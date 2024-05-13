package school.sptech.naumspringapi.mapper;

import org.springframework.stereotype.Component;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Agendamento;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoCriacaoDto;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoListagemDto;

import java.util.List;
import java.util.Objects;

@Component
public class AgendamentoMapper {

    public static AgendamentoListagemDto toDto(Agendamento entity) {
        if (Objects.isNull(entity)) return null;
        AgendamentoListagemDto dto = new AgendamentoListagemDto();
        dto.setId(entity.getId());
        dto.setDataHoraAgendamento(entity.getDataHoraAgendamento());
        dto.setCliente(ClienteMapper.toDto(entity.getCliente()));
        dto.setBarbeiro(BarbeiroMapper.toDto(entity.getBarbeiro()));
        return dto;
    }

    public static Agendamento toEntity(AgendamentoCriacaoDto dto, Cliente cliente, Barbeiro barbeiro) {
        if (Objects.isNull(dto)) return null;
        Agendamento entity = new Agendamento();
        entity.setDataHoraAgendamento(dto.getDataHoraAgendamneto());
        entity.setCliente(cliente);
        entity.setBarbeiro(barbeiro);
        return entity;
    }

    public static List<AgendamentoListagemDto> toDto(List<Agendamento> entities) {
        return entities.stream().map(AgendamentoMapper::toDto).toList();
    }
}
