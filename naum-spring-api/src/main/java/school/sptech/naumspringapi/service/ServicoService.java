package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.Servico;
import jakarta.persistence.EntityNotFoundException;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.mapper.ServicoMapper;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.ServicoRepository;
import school.sptech.naumspringapi.dto.servicoDto.ServicoCriacaoDto;
import school.sptech.naumspringapi.dto.servicoDto.ServicoListagemDto;
import school.sptech.naumspringapi.dto.servicoDto.ServicoAtualizacaoDto;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final BarbeariaService barbeariaService;

    public List<ServicoListagemDto> listarServicosPorBarbearia(Long idBarbearia) {
        return ServicoMapper.toDto(servicoRepository.findAllByBarbearia(barbeariaService.buscarPorId(idBarbearia)));
    }

    @Transactional
    public ServicoListagemDto criarServicoPorBarbearia(ServicoCriacaoDto servicoDto, Long idBarbearia) {
        try {
            if (Objects.isNull(servicoDto) || Objects.isNull(idBarbearia)) throw new BadRequestException();
            Barbearia barbearia = barbeariaService.buscarPorId(idBarbearia);
            return ServicoMapper.toDto(servicoRepository.save(ServicoMapper.toEntity(servicoDto, barbearia)));
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar serviço", e);
        }
    }

    public ServicoListagemDto buscarServicoPorId(Long idBarbearia, Long idServico) {
        try {
            if (Objects.isNull(idBarbearia) || Objects.isNull(idServico)) throw new BadRequestException();
            Servico servico = servicoRepository.findByIdAndBarbearia(idServico, barbeariaService.buscarPorId(idBarbearia));
            return ServicoMapper.toDto(servico);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar serviço", e);
        }
    }

    public ServicoListagemDto atualizarServicoPorId(Long idServico, ServicoAtualizacaoDto servicoDto) {
        try {
            if (Objects.isNull(servicoDto)) throw new BadRequestException();
            Servico servicoAtual = servicoRepository.findById(idServico).orElse(null);
            if (Objects.isNull(servicoAtual)) throw new EntityNotFoundException();
            servicoAtual.setNomeServico(servicoDto.getNomeServico());
            servicoAtual.setPreco(servicoDto.getPreco());
            return ServicoMapper.toDto(servicoRepository.save(servicoAtual));
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar serviço", e);
        }
    }

    @Transactional
    public void excluirServico(Long idServico) {
        try {
            servicoRepository.delete(servicoRepository.findById(idServico).orElseThrow());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar serviço", e);
        }
    }
}
