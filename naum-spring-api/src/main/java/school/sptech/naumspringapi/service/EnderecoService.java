package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import school.sptech.naumspringapi.entity.Endereco;
import school.sptech.naumspringapi.mapper.EnderecoMapper;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.BarbeiroRepository;
import school.sptech.naumspringapi.repository.EnderecoRepository;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaCriacaoDto;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final BarbeiroRepository barbeiroRepository;

    @Transactional
    public Endereco cadastrarEndereco(BarbeariaCriacaoDto barbeariaCriacaoDto) {
        try {
            if (Objects.isNull(barbeariaCriacaoDto)) throw new BadRequestException();
            Endereco endereco = EnderecoMapper.toEntity(barbeariaCriacaoDto.getEndereco());
            return enderecoRepository.save(endereco);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar agendamentos", e);
        }
    }

    @Transactional
    public Endereco atualizarEndereco(Long id, BarbeariaCriacaoDto barbeariaCriacaoDto) {
        try {
            if (Objects.isNull(barbeariaCriacaoDto) || Objects.isNull(id)) throw new BadRequestException();
            Endereco endereco = enderecoRepository.findById(id).orElse(null);
            if (Objects.isNull(endereco)) throw new EntityNotFoundException();

            endereco.setCidade(barbeariaCriacaoDto.getEndereco().getCidade());
            endereco.setCep(barbeariaCriacaoDto.getEndereco().getCep());
            endereco.setNumero(barbeariaCriacaoDto.getEndereco().getNumero());
            endereco.setBairro(barbeariaCriacaoDto.getEndereco().getBairro());
            endereco.setUf(barbeariaCriacaoDto.getEndereco().getUf());
            endereco.setRua(barbeariaCriacaoDto.getEndereco().getRua());
            return enderecoRepository.save(endereco);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar agendamentos", e);
        }
    }
}
