package school.sptech.naumspringapi.mapper;

import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaAtualizacaoDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaCriacaoDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.entity.Barbearia;

import java.util.List;

public class BarbeariaMapper {

    public static Barbearia toEntity(BarbeariaCriacaoDto dto) {
        if (dto == null) return null;

        Barbearia barbearia = new Barbearia();
        barbearia.setNome(dto.getNome());
        barbearia.setAtiva(dto.isAtiva());
        barbearia.setEndereco(dto.getEndereco());
        barbearia.setLinkBarbearia(dto.getLinkBarbearia());
        barbearia.setFotoBarbearia(dto.getFotoBarbearia());

        return barbearia;
    }

    public static BarbeariaListagemDto toDto(Barbearia entity) {
        if (entity == null) return null;

        BarbeariaListagemDto dto = new BarbeariaListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setFotoBarbearia(entity.getFotoBarbearia());
        dto.setLinkBarbearia(entity.getLinkBarbearia());
        dto.setEndereco(entity.getEndereco());

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
