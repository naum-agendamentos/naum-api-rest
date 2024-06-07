package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import school.sptech.naumspringapi.entity.*;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.email.EmailService;
import school.sptech.naumspringapi.exception.ConflitoException;
import school.sptech.naumspringapi.repository.ServicoRepository;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.exception.NaoEncontradoException;
import school.sptech.naumspringapi.repository.AgendamentoRepository;
import school.sptech.naumspringapi.exception.EntidadeImprocessavelException;

import java.util.Set;
import java.util.List;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ServicoRepository servicoRepository;
    private final BarbeiroService barbeiroService;
    private final ClienteService clienteService;
    private final EmailService emailService;

    @Transactional
    public Agendamento criarAgendamento(Long barbeiroId, Long clienteId, List<Long> servicoIds, LocalDateTime inicio) {
        Barbeiro barbeiro = barbeiroService.buscarPorId(barbeiroId);
        Cliente cliente = clienteService.buscarPorId(clienteId);

        // Verificar se todos os IDs de serviço são válidos
        Set<Long> servicosValidos = servicoIds.stream()
                .filter(servicoRepository::existsById)
                .collect(Collectors.toSet());

        if (servicosValidos.size() != servicoIds.size())
            throw new EntidadeImprocessavelException("Serviço (Um ou mais IDs de serviço são inválidos.)");

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
                throw new ConflitoException("Conflito de horário com outro agendamento.");
            }
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setBarbeiro(barbeiro);
        agendamento.setCliente(cliente);
        agendamento.setInicio(inicio);
        agendamento.setFim(fim);
        agendamento.setServicosIds(List.copyOf(servicosValidos)); // Converter Set para List
        agendamento.setValorTotal(valorTotal); // Definir o valor total

        // Enviar email
        emailService.sendEmail(cliente.getEmail());
        emailService.sendEmailBarbeiro(barbeiro.getEmail());

        return agendamentoRepository.save(agendamento);
    }

    public Agendamento buscarPorId(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Agendamento"));
    }

    public List<Agendamento> listarPorBarbeiro(Long barbeiroId) {
        return agendamentoRepository.findByBarbeiroId(barbeiroId);
    }

    public List<Agendamento> listarPorCliente(Long clienteId) {
        return agendamentoRepository.findByClienteId(clienteId);
    }

    boolean horarioConflitante(LocalDateTime inicio, LocalDateTime fim, Agendamento agendamentoExistente) {
        return inicio.isBefore(agendamentoExistente.getFim()) && fim.isAfter(agendamentoExistente.getInicio());
    }

    public List<Agendamento> agendamentosUltimoMes(List<Long> servicosId) {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusMonths(1);
        return agendamentoRepository.findByServicosIdsContainingAndDateRange(servicosId, startDate, endDate);
    }

    @Transactional
    public Agendamento atualizarAgendamento(Long idAgendamento, Long barbeiroId, Long clienteId, List<Long> servicoIds, LocalDateTime inicio){
        Agendamento agendamentoAtual = agendamentoRepository.findById(idAgendamento).orElseThrow(() -> new NaoEncontradoException("Agendamento"));
        Barbeiro barbeiro = barbeiroService.buscarPorId(barbeiroId);
        Cliente cliente = clienteService.buscarPorId(clienteId);

        Set<Long> servicosValidos = servicoIds.stream()
                .filter(servicoRepository::existsById)
                .collect(Collectors.toSet());

        if (servicosValidos.size() != servicoIds.size())
            throw new EntidadeImprocessavelException("Serviço (Um ou mais IDs de serviço são inválidos.)");

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
            if (!agendamentoExistente.getId().equals(idAgendamento) && horarioConflitante(inicio, fim, agendamentoExistente)) {
                throw new ConflitoException("Conflito de horário com outro agendamento.");
            }
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setId(agendamentoAtual.getId());
        agendamento.setBarbeiro(barbeiro);
        agendamento.setCliente(cliente);
        agendamento.setInicio(inicio);
        agendamento.setFim(fim);
        agendamento.setServicosIds(List.copyOf(servicosValidos)); // Converter Set para List
        agendamento.setValorTotal(valorTotal); // Definir o valor total

        return agendamentoRepository.save(agendamento);
    }

    @Transactional
    public void excluirAgendamento(Long idAgendamento) {
        agendamentoRepository.delete(agendamentoRepository.findById(idAgendamento).orElseThrow(() -> new NaoEncontradoException("Agendamento")));
    }
}