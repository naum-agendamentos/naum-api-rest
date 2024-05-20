package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import school.sptech.naumspringapi.entity.*;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.mapper.AgendamentoMapper;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.exception.NaoEncontradoException;
import school.sptech.naumspringapi.repository.AgendamentoRepository;
import school.sptech.naumspringapi.exception.EntidadeImprocessavelException;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoCriacaoDto;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoAtualizacaoDto;

import java.util.List;
import java.util.Objects;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final ClienteService clienteService;
    private final BarbeiroService barbeiroService;
    private final AgendamentoRepository agendamentoRepository;

    @Transactional
    public Agendamento criarAgendamento(AgendamentoCriacaoDto agendamentoCriacaoDto, Barbeiro barbeiro, Cliente cliente) {
            if (Objects.isNull(barbeiro) || Objects.isNull(cliente)) throw new EntidadeImprocessavelException("barbeiro ou cliente");
            return agendamentoRepository.save(AgendamentoMapper.toEntity(agendamentoCriacaoDto, cliente, barbeiro));
    }

    public List<Agendamento> listarAgendamentosPorBarbeiro(Long idBarbeiro) {
        if (Objects.isNull(idBarbeiro)) throw new EntidadeImprocessavelException("idBarbeiro");
        Barbeiro barbeiro = barbeiroService.buscarPorId(idBarbeiro);
        return agendamentoRepository.findByBarbeiro(barbeiro);
    }

    public List<Agendamento> listarAgendamentosPorCliente(Long idCliente) {
        if (Objects.isNull(idCliente)) throw new EntidadeImprocessavelException("idCliente");
        Cliente cliente = clienteService.buscarPorId(idCliente);
        return agendamentoRepository.findByCliente(cliente);
    }

    public List<Agendamento> listarAgendamentosPorClienteAndData (LocalDate data, Long idCliente) {
        if (Objects.isNull(data) || Objects.isNull(idCliente)) throw new EntidadeImprocessavelException("data ou idCliente");
        Cliente cliente = clienteService.buscarPorId(idCliente);
        return agendamentoRepository.findAllByDataHoraAgendamentoAndCliente(data, cliente);
    }

    public List<Agendamento> listarAgendamentosPorBarbeiroAndData(Long idBarbeiro, LocalDate data) {
        if (Objects.isNull(data) || Objects.isNull(idBarbeiro)) throw new EntidadeImprocessavelException("data ou idBarbeiro");
        Barbeiro barbeiro = barbeiroService.buscarPorId(idBarbeiro);
        return agendamentoRepository.findAllByDataHoraAgendamentoAndBarbeiro(data, barbeiro);
    }

//    public Agendamento atualizarAgendamentoPorId(Long idAgendamento, AgendamentoAtualizacaoDto agendamento) {
//        if (Objects.isNull(idAgendamento) || Objects.isNull(agendamento)) throw new EntidadeImprocessavelException("idAgendamento");
//        Agendamento agendamentoAtual = agendamentoRepository.findById(idAgendamento).orElseThrow(() -> new NaoEncontradoException("agendamento"));
//        agendamentoAtual.setDataHoraAgendamento(agendamento.getDataHoraAgendamneto());
//        agendamentoAtual.setServico(agendamento.getServicos());
//        return agendamentoRepository.save(agendamentoAtual);
//    }

    @Transactional
    public void delearAgendamento(Long idAgendamento) {
        if (Objects.isNull(idAgendamento)) throw new EntidadeImprocessavelException("idAgendamento");
        Agendamento agendamento = agendamentoRepository.findById(idAgendamento).orElseThrow(() -> new NaoEncontradoException("agendamento"));
        agendamentoRepository.delete(agendamento);
    }
}