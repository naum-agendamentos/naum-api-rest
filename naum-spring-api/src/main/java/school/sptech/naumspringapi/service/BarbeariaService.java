package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import school.sptech.naumspringapi.entity.Endereco;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.mapper.BarbeariaMapper;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.BarbeariaRepository;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaCriacaoDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaAtualizacaoDto;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BarbeariaService {

    private final BarbeariaRepository barbeariaRepository;
    private final EnderecoService enderecoService;

    @Transactional
    public BarbeariaListagemDto criarBarbearia(BarbeariaCriacaoDto barbeariaDto) {
        try {
            if (Objects.isNull(barbeariaDto)) throw new BadRequestException();
            Barbearia barbearia = BarbeariaMapper.toEntity(barbeariaDto);
            if (!Objects.isNull(barbeariaDto.getEndereco())) {
                Endereco endereco = enderecoService.cadastrarEndereco(barbeariaDto);
                barbearia.setEndereco(endereco);
            } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O endereço não pode ser nulo");
            barbearia.setAtiva(true);
            Barbearia barbeariaSalva = barbeariaRepository.save(barbearia);
            return BarbeariaMapper.toDto(barbeariaSalva);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar agendamentos", e);
        }
    }

    public List<BarbeariaListagemDto> listarBarbearia() {
        try {
            List<Barbearia> barbearias = barbeariaRepository.findByAtivaTrue();
            if (barbearias.isEmpty()) return null;
            return BarbeariaMapper.toDto(barbearias);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar barbearias", e);
        }
    }

    @Transactional
    public BarbeariaListagemDto atualizarBarbearia(Long id, BarbeariaCriacaoDto barbeariaAtualizada) {
        try {
            if (Objects.isNull(barbeariaAtualizada) || Objects.isNull(id)) throw new BadRequestException();
            Barbearia barbeariaAtual = barbeariaRepository.findById(id).orElse(null);
            if (Objects.isNull(barbeariaAtual)) throw new EntityNotFoundException();

            // Atualiza os dados da barbearia
            barbeariaAtual.setNome(barbeariaAtualizada.getNome());
            barbeariaAtual.setLinkBarbearia(barbeariaAtualizada.getLinkBarbearia());
            barbeariaAtual.setFotoBarbearia(barbeariaAtualizada.getFotoBarbearia());

            if (barbeariaAtualizada.getEndereco() != null) {
                Endereco endereco = enderecoService.atualizarEndereco(id, barbeariaAtualizada);
                barbeariaAtual.setEndereco(endereco);
            }

            // Salva a barbearia, que deve manter a referência para o mesmo endereço já existente
            Barbearia barbeariaSalva = barbeariaRepository.save(barbeariaAtual);
            return BarbeariaMapper.toDto(barbeariaSalva);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar barbearia", e);
        }
    }

    public BarbeariaAtualizacaoDto desativarBarbearia(Long id){
        try {
            if (Objects.isNull(id)) throw new BadRequestException();
            Barbearia barbearia = barbeariaRepository.findById(id).orElse(null);
            if (Objects.isNull(barbearia)) throw new EntityNotFoundException();
            barbearia.setAtiva(false);
            return BarbeariaMapper.toAttDto(barbeariaRepository.save(barbearia));
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao desativar barbearia", e);
        }
    }

    public Barbearia buscarPorId(Long idBarbearia) {
        Barbearia barbearia = barbeariaRepository.findById(idBarbearia).orElse(null);
        if (Objects.isNull(barbearia)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada");
        return barbearia;
    }
}
