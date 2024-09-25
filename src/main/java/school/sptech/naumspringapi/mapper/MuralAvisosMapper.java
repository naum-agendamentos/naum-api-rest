package school.sptech.naumspringapi.mapper;

import org.springframework.stereotype.Component;
import school.sptech.naumspringapi.dto.muralAvisosDto.MuralAvisosCriacaoDto;
import school.sptech.naumspringapi.dto.muralAvisosDto.MuralAvisosListagemDto;
import school.sptech.naumspringapi.dto.muralAvisosDto.MuralAvisosAtualizacaoDto;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.MuralAvisos;

import java.util.List;
import java.util.Objects;

@Component
public class MuralAvisosMapper {

    public static MuralAvisos toEntity(MuralAvisosCriacaoDto dto, Barbeiro barbeiro) {
        if (Objects.isNull(dto)) return null;

        MuralAvisos muralAvisos = new MuralAvisos();
        muralAvisos.setTitulo(dto.getTitulo());
        muralAvisos.setDescricao(dto.getDescricao());
        muralAvisos.setUrl(dto.getUrl());
        muralAvisos.setTipoAviso(dto.getTipoAviso());
        muralAvisos.setBarbeiro(barbeiro);

        return muralAvisos;
    }

    public static MuralAvisosListagemDto toDto(MuralAvisos entity) {
        if (Objects.isNull(entity)) return null;

        MuralAvisosListagemDto dto = new MuralAvisosListagemDto();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setDescricao(entity.getDescricao());
        dto.setUrl(entity.getUrl());
        dto.setData(entity.getData());
        dto.setTipoAviso(entity.getTipoAviso());
        dto.setBarbeiro(entity.getBarbeiro());

        return dto;
    }

    public static List<MuralAvisosListagemDto> toDto(List<MuralAvisos> entities) {
        return entities.stream().map(MuralAvisosMapper::toDto).toList();
    }

    public static MuralAvisos toEntity(MuralAvisosAtualizacaoDto dto, MuralAvisos entity) {
        if (Objects.isNull(dto) || Objects.isNull(entity)) return null;

        entity.setTitulo(dto.getTitulo());
        entity.setDescricao(dto.getDescricao());
        entity.setUrl(dto.getUrl());
        entity.setTipoAviso(dto.getTipoAviso());

        return entity;
    }
}
