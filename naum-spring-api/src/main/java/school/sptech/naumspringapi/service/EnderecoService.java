package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaCriacaoDto;
import school.sptech.naumspringapi.dto.enderecoDto.EnderecoCriacaoDto;
import school.sptech.naumspringapi.dto.enderecoDto.EnderecoListagemDto;
import school.sptech.naumspringapi.entity.Endereco;
import school.sptech.naumspringapi.mapper.EnderecoMapper;
import school.sptech.naumspringapi.repository.EnderecoRepository;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public EnderecoListagemDto cadastrarEndereco(BarbeariaCriacaoDto barbeariaCriacaoDto) {
        Endereco endereco = barbeariaCriacaoDto.getEndereco();
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        return EnderecoMapper.toDto(enderecoSalvo);
    }
}
