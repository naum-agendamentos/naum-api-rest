package school.sptech.naumspringapi.mapper;

import org.springframework.stereotype.Component;
import school.sptech.naumspringapi.entity.Endereco;
import school.sptech.naumspringapi.dto.enderecoDto.EnderecoCriacaoDto;
import school.sptech.naumspringapi.dto.enderecoDto.EnderecoListagemDto;

import java.util.Objects;

@Component
public class EnderecoMapper {
    public static Endereco toEntity(EnderecoCriacaoDto dto) {
        if (Objects.isNull(dto)) return null;

        Endereco endereco = new Endereco();
        endereco.setCidade(dto.getCidade());
        endereco.setCep(dto.getCep());
        endereco.setNumero(dto.getNumero());
        endereco.setBairro(dto.getBairro());
        endereco.setUf(dto.getUf());
        endereco.setRua(dto.getRua());

        return endereco;
    }

    public static EnderecoListagemDto toDto(Endereco entity) {
        if (Objects.isNull(entity)) return null;

        EnderecoListagemDto enderecoDto = new EnderecoListagemDto();
        enderecoDto.setId(entity.getId());
        enderecoDto.setCidade(entity.getCidade());
        enderecoDto.setCep(entity.getCep());
        enderecoDto.setNumero(entity.getNumero());
        enderecoDto.setBairro(entity.getBairro());
        enderecoDto.setUf(entity.getUf());
        enderecoDto.setRua(entity.getRua());

        return enderecoDto;
    }
}
