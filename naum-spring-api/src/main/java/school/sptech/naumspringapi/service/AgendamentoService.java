package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import school.sptech.naumspringapi.entity.*;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import school.sptech.naumspringapi.mapper.AgendamentoMapper;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.AgendamentoRepository;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoCriacaoDto;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoListagemDto;
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
    public AgendamentoListagemDto criarAgendamento(AgendamentoCriacaoDto agendamentoCriacaoDto, Barbeiro barbeiro, Cliente cliente) {
        try {
            if (Objects.isNull(barbeiro) || Objects.isNull(cliente)) throw new BadRequestException();
            Agendamento agendamento = agendamentoRepository.save(AgendamentoMapper.toEntity(agendamentoCriacaoDto, cliente, barbeiro));
            return AgendamentoMapper.toDto(agendamento);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar agendamento", e);
        }
    }

    public List<AgendamentoListagemDto> listarAgendamentosPorBarbeiro(Long idBarbeiro) {
        try {
            if (Objects.isNull(idBarbeiro)) throw new BadRequestException();
            Barbeiro barbeiro = barbeiroService.buscarPorId(idBarbeiro);
            return AgendamentoMapper.toDto(agendamentoRepository.findByBarbeiro(barbeiro));
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar agendamentos", e);
        }
    }

    public List<AgendamentoListagemDto> listarAgendamentosPorCliente(Long idCliente) {
        try {
            if (Objects.isNull(idCliente)) throw new BadRequestException();
            Cliente cliente = clienteService.buscarPorId(idCliente);
            return AgendamentoMapper.toDto(agendamentoRepository.findByCliente(cliente));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar agendamentos", e);
        }
    }

    public List<AgendamentoListagemDto> listarAgendamentosPorClienteAndData (LocalDate data, Long idCliente) {
        try {
            if (Objects.isNull(data) || Objects.isNull(idCliente)) throw new BadRequestException();
            Cliente cliente = clienteService.buscarPorId(idCliente);
            return AgendamentoMapper.toDto(agendamentoRepository.findAllByDataHoraAgendamentoAndCliente(data, cliente));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar agendamentos", e);
        }
    }

    public List<AgendamentoListagemDto> listarAgendamentosPorBarbeiroAndData(Long idBarbeiro, LocalDate data) {
        try {
            if (Objects.isNull(data) || Objects.isNull(idBarbeiro)) throw new BadRequestException();
            Barbeiro barbeiro = barbeiroService.buscarPorId(idBarbeiro);
            return AgendamentoMapper.toDto(agendamentoRepository.findAllByDataHoraAgendamentoAndBarbeiro(data, barbeiro));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar agendamentos", e);
        }
    }

    public AgendamentoListagemDto atualizarAgendamentoPorId(Long idAgendamento, AgendamentoAtualizacaoDto agendamento) {
        try {
            if (Objects.isNull(idAgendamento) || Objects.isNull(agendamento)) throw new BadRequestException();
            Agendamento agendamentoAtual = agendamentoRepository.findById(idAgendamento).orElse(null);
            if (Objects.isNull(agendamentoAtual)) throw new EntityNotFoundException();
            agendamentoAtual.setDataHoraAgendamento(agendamento.getDataHoraAgendamneto());
            agendamentoAtual.setServico(agendamento.getServicos());
            return AgendamentoMapper.toDto(agendamentoRepository.save(agendamentoAtual));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar agendamento", e);
        }
    }

    @Transactional
    public void delearAgendamento(Long idAgendamento) {
        try {
            if (Objects.isNull(idAgendamento)) throw new BadRequestException();
            Agendamento agendamento = agendamentoRepository.findById(idAgendamento).orElse(null);
            if (Objects.isNull(agendamento)) throw new EntityNotFoundException();
            agendamentoRepository.delete(agendamento);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar agendamento", e);
        }
    }
}