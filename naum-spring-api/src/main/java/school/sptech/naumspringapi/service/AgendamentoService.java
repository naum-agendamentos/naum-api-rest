package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import school.sptech.naumspringapi.entity.*;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.mapper.AgendamentoMapper;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.AgendamentoRepository;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoCriacaoDto;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoListagemDto;

import java.time.LocalDate;
import java.util.List;


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

    public List<AgendamentoListagemDto> listarAgendamentosPorBarbeiro(Integer idBarbeiro) {
        if (idBarbeiro == null) return null;
        Barbeiro barbeiro = barbeiroService.buscarPorId(idBarbeiro);
        return AgendamentoMapper.toDto(agendamentoRepository.findByBarbeiro(barbeiro));
    }

    public List<AgendamentoListagemDto> listarAgendamentosPorCliente(Integer idCliente) {
        if (idCliente == null) return null;
        Cliente cliente = clienteService.buscarPorId(idCliente);
        return AgendamentoMapper.toDto(agendamentoRepository.findByCliente(cliente));
    }

    public List<AgendamentoListagemDto> listarAgendamentosPorClienteAndData (LocalDate data, Integer idCliente) {
        if (data == null || idCliente == null) return null;
        Cliente cliente = clienteService.buscarPorId(idCliente);
        return AgendamentoMapper.toDto(agendamentoRepository.findAllByDataAgendamentoAndCliente(data, cliente));
    }

    public List<AgendamentoListagemDto> listarAgendamentosPorBarbeiroAndData(Integer idBarbeiro, LocalDate data) {
        if (data == null || idBarbeiro == null) return null;
        Barbeiro barbeiro = barbeiroService.buscarPorId(idBarbeiro);
        return AgendamentoMapper.toDto(agendamentoRepository.findAllByDataAgendamentoAndBarbeiro(data, barbeiro));
    }
}