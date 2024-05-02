package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import school.sptech.naumspringapi.entity.*;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.mapper.AgendamentoMapper;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.AgendamentoRepository;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoCriacaoDto;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoListagemDto;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoAtualizacaoDto;

import java.util.List;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final ClienteService clienteService;
    private final BarbeiroService barbeiroService;
    private final AgendamentoRepository agendamentoRepository;

    @Transactional
    public AgendamentoListagemDto criarAgendamento(AgendamentoCriacaoDto agendamentoCriacaoDto, Barbeiro barbeiro, Cliente cliente) {
        if (barbeiro == null || cliente == null) return null;
        Agendamento agendamento = agendamentoRepository.save(AgendamentoMapper.toEntity(agendamentoCriacaoDto, cliente, barbeiro));
        return AgendamentoMapper.toDto(agendamento);
    }

    public List<AgendamentoListagemDto> listarAgendamentosPorBarbeiro(Long idBarbeiro) {
        if (idBarbeiro == null) return null;
        Barbeiro barbeiro = barbeiroService.buscarPorId(idBarbeiro);
        return AgendamentoMapper.toDto(agendamentoRepository.findByBarbeiro(barbeiro));
    }

    public List<AgendamentoListagemDto> listarAgendamentosPorCliente(Long idCliente) {
        if (idCliente == null) return null;
        Cliente cliente = clienteService.buscarPorId(idCliente);
        return AgendamentoMapper.toDto(agendamentoRepository.findByCliente(cliente));
    }

    public List<AgendamentoListagemDto> listarAgendamentosPorClienteAndData (LocalDate data, Long idCliente) {
        if (data == null || idCliente == null) return null;
        Cliente cliente = clienteService.buscarPorId(idCliente);
        return AgendamentoMapper.toDto(agendamentoRepository.findAllByDataHoraAgendamentoAndCliente(data, cliente));
    }

    public List<AgendamentoListagemDto> listarAgendamentosPorBarbeiroAndData(Long idBarbeiro, LocalDate data) {
        if (data == null || idBarbeiro == null) return null;
        Barbeiro barbeiro = barbeiroService.buscarPorId(idBarbeiro);
        return AgendamentoMapper.toDto(agendamentoRepository.findAllByDataHoraAgendamentoAndBarbeiro(data, barbeiro));
    }

    public AgendamentoListagemDto atualizarAgendamentoPorId(Long idAgendamento, AgendamentoAtualizacaoDto agendamento) {
        if (idAgendamento == null || agendamento == null) return null;
        Agendamento agendamentoAtual = agendamentoRepository.findById(idAgendamento).orElseThrow();
        agendamentoAtual.setDataHoraAgendamento(agendamento.getDataHoraAgendamneto());
        agendamentoAtual.setServico(agendamento.getServicos());
        return AgendamentoMapper.toDto(agendamentoRepository.save(agendamentoAtual));
    }

    public void delearAgendamento(Long idAgendamento) {
        agendamentoRepository.delete(agendamentoRepository.findById(idAgendamento).orElseThrow());
    }
}