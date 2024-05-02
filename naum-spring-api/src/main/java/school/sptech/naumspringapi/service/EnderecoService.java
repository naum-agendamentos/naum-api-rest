package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.Endereco;
import school.sptech.naumspringapi.mapper.EnderecoMapper;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.EnderecoRepository;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaCriacaoDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Transactional
    public Endereco cadastrarEndereco(BarbeariaCriacaoDto barbeariaCriacaoDto) {
        Endereco endereco = EnderecoMapper.toEntity(barbeariaCriacaoDto.getEndereco());
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        return enderecoSalvo;
    }

    @Transactional
    public Endereco atualizarEndereco(Long id, BarbeariaCriacaoDto barbeariaCriacaoDto) {
        Optional<Endereco> enderecoOpt = enderecoRepository.findById(id);

        if(enderecoOpt.isEmpty()) return null;

        Endereco endereco = enderecoOpt.get();

        endereco.setCidade(barbeariaCriacaoDto.getEndereco().getCidade());
        endereco.setCep(barbeariaCriacaoDto.getEndereco().getCep());
        endereco.setNumero(barbeariaCriacaoDto.getEndereco().getNumero());
        endereco.setBairro(barbeariaCriacaoDto.getEndereco().getBairro());
        endereco.setUf(barbeariaCriacaoDto.getEndereco().getUf());
        endereco.setRua(barbeariaCriacaoDto.getEndereco().getRua());
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        return enderecoSalvo;
    }
}
