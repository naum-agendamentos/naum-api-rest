package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import school.sptech.naumspringapi.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.exception.NaoEncontradoException;
import school.sptech.naumspringapi.repository.AgendamentoRepository;
import school.sptech.naumspringapi.exception.EntidadeImprocessavelException;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoCriacaoDto;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoAtualizacaoDto;
import school.sptech.naumspringapi.repository.BarbeiroRepository;
import school.sptech.naumspringapi.repository.ClienteRepository;
import school.sptech.naumspringapi.repository.ServicoRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Transactional
    public Agendamento criarAgendamento(Long barbeiroId, Long clienteId, List<Long> servicoIds, LocalDateTime inicio) {
        Barbeiro barbeiro = barbeiroRepository.findById(barbeiroId)
                .orElseThrow(() -> new IllegalArgumentException("Barbeiro não encontrado"));
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        // Verificar se todos os IDs de serviço são válidos
        Set<Long> servicosValidos = servicoIds.stream()
                .filter(id -> servicoRepository.existsById(id))
                .collect(Collectors.toSet());

        if (servicosValidos.size() != servicoIds.size()) {
            throw new IllegalArgumentException("Um ou mais IDs de serviço são inválidos.");
        }

        // Calcular a data de fim com base na duração dos serviços
        List<Servico> servicos = servicoRepository.findAllById(servicosValidos);
        Duration duracaoTotal = servicos.stream()
                .map(servico -> Duration.ofMinutes(servico.getTempoServico())) // Converter para Duration
                .reduce(Duration.ZERO, Duration::plus);
        LocalDateTime fim = inicio.plus(duracaoTotal);

        // Calcular o valor total dos serviços
        double valorTotal = servicos.stream()
                .mapToDouble(Servico::getPreco) // Supondo que o método getPreco retorna o preço do serviço
                .sum();

        // Verificar conflitos de horário
        List<Agendamento> agendamentosExistentes = agendamentoRepository.findByBarbeiroId(barbeiroId);
        for (Agendamento agendamentoExistente : agendamentosExistentes) {
            if (horarioConflitante(inicio, fim, agendamentoExistente)) {
                throw new IllegalArgumentException("Conflito de horário com outro agendamento.");
            }
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setBarbeiro(barbeiro);
        agendamento.setCliente(cliente);
        agendamento.setInicio(inicio);
        agendamento.setFim(fim);
        agendamento.setServicosIds(List.copyOf(servicosValidos)); // Converter Set para List
        agendamento.setValorTotal(valorTotal); // Definir o valor total

        return agendamentoRepository.save(agendamento);
    }

    public Agendamento buscarPorId(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));
    }

    public List<Agendamento> listarPorBarbeiro(Long barbeiroId) {
        return agendamentoRepository.findByBarbeiroId(barbeiroId);
    }

    public List<Agendamento> listarPorCliente(Long clienteId) {
        return agendamentoRepository.findByClienteId(clienteId);
    }

    private boolean horarioConflitante(LocalDateTime inicio, LocalDateTime fim, Agendamento agendamentoExistente) {
        return inicio.isBefore(agendamentoExistente.getFim()) && fim.isAfter(agendamentoExistente.getInicio());
    }
}