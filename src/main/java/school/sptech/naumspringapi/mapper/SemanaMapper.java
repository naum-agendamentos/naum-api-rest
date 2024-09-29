package school.sptech.naumspringapi.mapper;

import school.sptech.naumspringapi.dto.semanaDto.SemanaCriacaoDto;
import school.sptech.naumspringapi.dto.semanaDto.SemanaListagemDto;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Semana;

import java.util.*;

public class SemanaMapper {

    public static Semana toEntity(SemanaCriacaoDto semanaCriacaoDto){
        if(Objects.isNull(semanaCriacaoDto)) return null;

        Semana semana = new Semana();
        semana.setSegunda(semanaCriacaoDto.getSegunda().get("Segunda"));
        semana.setTerca(semanaCriacaoDto.getTerca().get("Terca"));
        semana.setQuarta(semanaCriacaoDto.getQuarta().get("Quarta"));
        semana.setQuinta(semanaCriacaoDto.getQuinta().get("Quinta"));
        semana.setSexta(semanaCriacaoDto.getSexta().get("Sexta"));
        semana.setSabado(semanaCriacaoDto.getSabado().get("Sabado"));
        semana.setDomingo(semanaCriacaoDto.getDomingo().get("Domingo"));

        return semana;
    }

    public static SemanaListagemDto toDto(Semana entity) {
       if (Objects.isNull(entity)) return null;
       SemanaListagemDto semanaDto = new SemanaListagemDto();
       semanaDto.setSegunda("Segunda",entity.isSegunda());
       semanaDto.setTerca("Terca",entity.isTerca());
       semanaDto.setQuarta("Quarta",entity.isQuarta());
       semanaDto.setQuinta("Quinta",entity.isQuinta());
       semanaDto.setSexta("Sexta",entity.isSexta());
       semanaDto.setSabado("Sabado",entity.isSabado());
       semanaDto.setDomingo("Domingo",entity.isDomingo());
       return semanaDto;
    }

    public static List<SemanaListagemDto> toDto(List<Semana> entities) {
        if (Objects.isNull(entities)) return null;
        return entities.stream()
                .map(SemanaMapper::toDto)
                .toList();
    }

}
