package school.sptech.naumspringapi.mapper;

import school.sptech.naumspringapi.dto.enderecoDto.EnderecoCriacaoDto;
import school.sptech.naumspringapi.dto.enderecoDto.EnderecoListagemDto;
import school.sptech.naumspringapi.entity.Endereco;

public class EnderecoMapper {
    public static Endereco toEntity(EnderecoCriacaoDto dto) {
        if (dto == null) return null;

        Endereco endereco = new Endereco();
        endereco.setBairro(dto.getBairro());
        endereco.setCep(dto.getCep());
        endereco.setCidade(dto.getCidade());
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setUf(dto.getUf());

        return endereco;
    }

    public static EnderecoListagemDto toDto(Endereco entity) {
        if (entity == null) return null;

        EnderecoListagemDto enderecoDto = new EnderecoListagemDto();
        enderecoDto.setRua(entity.getRua());
        enderecoDto.setBairro(entity.getBairro());
        enderecoDto.setCep(entity.getCep());
        enderecoDto.setNumero(entity.getNumero());
        enderecoDto.setUf(entity.getUf());
        enderecoDto.setCidade(entity.getCidade());

        return enderecoDto;
    }
}
