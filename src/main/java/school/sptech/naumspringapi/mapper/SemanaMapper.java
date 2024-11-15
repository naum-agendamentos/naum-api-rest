package school.sptech.naumspringapi.mapper;

import school.sptech.naumspringapi.entity.Semana;
import school.sptech.naumspringapi.dto.semanaDto.SemanaCriacaoDto;
import school.sptech.naumspringapi.dto.semanaDto.SemanaListagemDto;

import java.util.*;

public class SemanaMapper {

    public static Semana toEntity(SemanaCriacaoDto semanaCriacaoDto){
        if(Objects.isNull(semanaCriacaoDto)) return null;

        Semana semana = new Semana();
        semana.setSegunda(semanaCriacaoDto.getSegunda());
        semana.setTerca(semanaCriacaoDto.getTerca());
        semana.setQuarta(semanaCriacaoDto.getQuarta());
        semana.setQuinta(semanaCriacaoDto.getQuinta());
        semana.setSexta(semanaCriacaoDto.getSexta());
        semana.setSabado(semanaCriacaoDto.getSabado());
        semana.setDomingo(semanaCriacaoDto.getDomingo());

        return semana;
    }

    public static SemanaListagemDto toDto(Semana entity) {
       if (Objects.isNull(entity)) return null;
       SemanaListagemDto semanaDto = new SemanaListagemDto();
       semanaDto.setSegunda(entity.getSegunda());
       semanaDto.setTerca(entity.getTerca());
       semanaDto.setQuarta(entity.getQuarta());
       semanaDto.setQuinta(entity.getQuinta());
       semanaDto.setSexta(entity.getSexta());
       semanaDto.setSabado(entity.getSabado());
       semanaDto.setDomingo(entity.getDomingo());
       return semanaDto;
    }

    public static List<SemanaListagemDto> toDto(List<Semana> entities) {
        if (Objects.isNull(entities)) return null;
        return entities.stream()
                .map(SemanaMapper::toDto)
                .toList();
    }

}
