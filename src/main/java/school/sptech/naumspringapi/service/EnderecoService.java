package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.Endereco;
import school.sptech.naumspringapi.mapper.EnderecoMapper;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.EnderecoRepository;
import school.sptech.naumspringapi.exception.NaoEncontradoException;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaCriacaoDto;
import school.sptech.naumspringapi.exception.EntidadeImprocessavelException;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Transactional
    public Endereco cadastrarEndereco(BarbeariaCriacaoDto barbeariaCriacaoDto) {
        if (Objects.isNull(barbeariaCriacaoDto)) throw new EntidadeImprocessavelException("Barbearia");
        Endereco endereco = EnderecoMapper.toEntity(barbeariaCriacaoDto.getEndereco());
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public Endereco atualizarEndereco(Long id, BarbeariaCriacaoDto barbeariaCriacaoDto) {
        if (Objects.isNull(barbeariaCriacaoDto) || Objects.isNull(id)) throw new EntidadeImprocessavelException("Id ou barbearia");
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Endereco"));

        endereco.setCidade(barbeariaCriacaoDto.getEndereco().getCidade());
        endereco.setCep(barbeariaCriacaoDto.getEndereco().getCep());
        endereco.setNumero(barbeariaCriacaoDto.getEndereco().getNumero());
        endereco.setBairro(barbeariaCriacaoDto.getEndereco().getBairro());
        endereco.setUf(barbeariaCriacaoDto.getEndereco().getUf());
        endereco.setRua(barbeariaCriacaoDto.getEndereco().getRua());
        return enderecoRepository.save(endereco);
    }
}
