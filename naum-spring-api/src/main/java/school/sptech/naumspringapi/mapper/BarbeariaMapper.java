package school.sptech.naumspringapi.mapper;

import lombok.RequiredArgsConstructor;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaAtualizacaoDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaCriacaoDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.entity.Endereco;

import java.util.List;

@RequiredArgsConstructor
public class BarbeariaMapper {

    public static Barbearia toEntity(BarbeariaCriacaoDto dto) {
        if (dto == null) return null;

        Barbearia barbearia = new Barbearia();
        barbearia.setNome(dto.getNome());
        barbearia.setLinkBarbearia(dto.getLinkBarbearia());
        barbearia.setFotoBarbearia(dto.getFotoBarbearia());

        if (dto.getEndereco() != null) {
            // Convertendo o EnderecoCriacaoDto para Endereco
            Endereco endereco = EnderecoMapper.toEntity(dto.getEndereco());
            barbearia.setEndereco(endereco);
        }

        return barbearia;
    }

    public static BarbeariaListagemDto toDto(Barbearia entity) {
        if (entity == null) return null;

        BarbeariaListagemDto dto = new BarbeariaListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setLinkBarbearia(entity.getLinkBarbearia());
        dto.setFotoBarbearia(entity.getFotoBarbearia());

        if (entity.getEndereco() != null) {
            // Mapear o endereço para EnderecoListagemDto
            dto.setEndereco(EnderecoMapper.toDto(entity.getEndereco()));
        }

        return dto;
    }

    public static List<BarbeariaListagemDto> toDto(List<Barbearia> entities) {
        return entities.stream().map(BarbeariaMapper::toDto).toList();
    }

    public static BarbeariaAtualizacaoDto toAttDto(Barbearia entity) {
        if (entity == null) return null;

        BarbeariaAtualizacaoDto dto = new BarbeariaAtualizacaoDto();
        dto.setNome(entity.getNome());
        dto.setFotoBarbearia(entity.getFotoBarbearia());
        dto.setLinkBarbearia(entity.getLinkBarbearia());
        dto.setAtiva(entity.isAtiva());

        return dto;
    }


}
