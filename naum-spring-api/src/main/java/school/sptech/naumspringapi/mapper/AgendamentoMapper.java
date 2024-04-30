package school.sptech.naumspringapi.mapper;

import lombok.RequiredArgsConstructor;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoCriacaoDto;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoListagemDto;
import school.sptech.naumspringapi.entity.Agendamento;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Cliente;

import java.util.List;

@RequiredArgsConstructor
public class AgendamentoMapper {

    public static AgendamentoListagemDto toDto(Agendamento entity) {
        if (entity == null) return null;
        AgendamentoListagemDto dto = new AgendamentoListagemDto();
        dto.setId(entity.getId());
        dto.setDataHoraAgendamento(entity.getDataHoraAgendamento());
        dto.setCliente(ClienteMapper.toDto(entity.getCliente()));
        dto.setServicos(ServicoMapper.toDto(entity.getServico()));
        dto.setBarbeiro(BarbeiroMapper.toDto(entity.getBarbeiro()));
        return dto;
    }

    public static Agendamento toEntity(AgendamentoCriacaoDto dto, Cliente cliente, Barbeiro barbeiro) {
        if (dto == null) return null;
        Agendamento entity = new Agendamento();
        entity.setDataHoraAgendamento(dto.getDataHoraAgendamneto());
        entity.setCliente(cliente);
        entity.setBarbeiro(barbeiro);
        entity.setServico(dto.getServicos());
        return entity;
    }

    public static List<AgendamentoListagemDto> toDto(List<Agendamento> entities) {
        return entities.stream().map(AgendamentoMapper::toDto).toList();
    }
}
