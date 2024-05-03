package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.Cliente;
import jakarta.persistence.EntityNotFoundException;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.entity.Avaliacao;
import school.sptech.naumspringapi.mapper.AvaliacaoMapper;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.AvaliacaoRepository;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoCriacaoDto;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoListagemDto;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoAtualizacaoDto;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final BarbeariaService barbeariaService;
    private final ClienteService clienteService;

    @Transactional
    public AvaliacaoListagemDto criarAvaliacao (AvaliacaoCriacaoDto avaliacao, Long clienteId, Long barbeariaId) {
        try {
            if (Objects.isNull(avaliacao) || Objects.isNull(clienteId) || Objects.isNull(barbeariaId)) throw new BadRequestException();
            Cliente cliente = clienteService.buscarPorId(clienteId);
            Barbearia barbearia = barbeariaService.buscarPorId(barbeariaId);
            return AvaliacaoMapper.toDto(avaliacaoRepository.save(AvaliacaoMapper.toEntity(avaliacao, cliente, barbearia)));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar avaliação", e);
        }
    }

    public List<AvaliacaoListagemDto> listarAvaliacoesPorBarbearia (Long barbeariaId) {
        try {
            if (Objects.isNull(barbeariaId)) throw new BadRequestException();
            Barbearia barbaeria = barbeariaService.buscarPorId(barbeariaId);
            return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByBarbearia(barbaeria));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar avaliações", e);
        }
    }

    public List<AvaliacaoListagemDto> listarAvaliacoesPorCliente (Long clienteId) {
        try {
            if (Objects.isNull(clienteId)) throw new BadRequestException();
            Cliente cliente = clienteService.buscarPorId(clienteId);
            return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByCliente(cliente));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar avaliações", e);
        }
    }

    public List<AvaliacaoListagemDto> listarAvaliacoesPorEstrela (Integer estrela) {
        try {
            if (Objects.isNull(estrela)) throw new BadRequestException();
            return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByQtdEstrela(estrela));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar avaliações", e);
        }
    }

    public List<AvaliacaoListagemDto> listarAvaliacoesPorEstrelaAndBarbearia(Integer estrela, Long barbeariaId) {
        try {
            if (Objects.isNull(estrela) || Objects.isNull(barbeariaId)) throw new BadRequestException();
            Barbearia barbearia = barbeariaService.buscarPorId(barbeariaId);
            return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByBarbeariaAndQtdEstrela(barbearia, estrela));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar avaliações", e);
        }
    }

    public List<AvaliacaoListagemDto> listarAvaliacaoDinamica(Integer estrela, Long barbeariaId, Long clienteId) {
        try {
            if (Objects.isNull(estrela) && Objects.isNull(clienteId))
                return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByBarbearia(barbeariaService.buscarPorId(barbeariaId)));
            else if (Objects.isNull(estrela) && Objects.isNull(barbeariaId))
                return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByCliente(clienteService.buscarPorId(clienteId)));
            else if (Objects.isNull(clienteId))
                return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByBarbeariaAndQtdEstrela(barbeariaService.buscarPorId(barbeariaId), estrela));
            else if (Objects.isNull(barbeariaId))
                return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByClienteAndQtdEstrela(clienteService.buscarPorId(clienteId), estrela));
            else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao listar avaliação");
        }  catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar avaliações", e);
        }
    }

    @Transactional
    public AvaliacaoListagemDto atualizarAvaliacao(Long idAvaliacao, AvaliacaoAtualizacaoDto avaliacaoAtualizacaoDto) {
        try {
            if (Objects.isNull(idAvaliacao)) throw new BadRequestException();
            Avaliacao avaliacaoAtual = avaliacaoRepository.findById(idAvaliacao).orElseThrow();
            avaliacaoAtual.setQtdEstrela(avaliacaoAtualizacaoDto.getQtdEstrela());
            return AvaliacaoMapper.toDto(avaliacaoRepository.save(avaliacaoAtual));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar avaliação", e);
        }
    }

    @Transactional
    public void deletarAvaliacao(Long idAvaliacao) {
        try {
            if (Objects.isNull(idAvaliacao)) throw new BadRequestException();
            avaliacaoRepository.delete(avaliacaoRepository.findById(idAvaliacao).orElseThrow());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar avaliação", e);
        }
    }
}
