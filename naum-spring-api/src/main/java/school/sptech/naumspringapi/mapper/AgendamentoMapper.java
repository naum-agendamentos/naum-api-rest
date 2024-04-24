package school.sptech.naumspringapi.mapper;

import lombok.RequiredArgsConstructor;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoCriacaoDto;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoListagemDto;
import school.sptech.naumspringapi.entity.Agendamento;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Cliente;

@RequiredArgsConstructor
public class AgendamentoMapper {

    public static AgendamentoListagemDto toDto(Agendamento entity) {
        if (entity == null) return null;
        AgendamentoListagemDto dto = new AgendamentoListagemDto();
        dto.setId(entity.getId());
        dto.setDataAgendamento(entity.getDataAgendamento());
        dto.setBarbeiro(BarbeiroMapper.toDto(entity.getBarbeiro()));
        dto.setServicos(ServicoMapper.toDto(entity.getServico()));
        dto.setCliente(ClienteMapper.toDto(entity.getCliente()));

        return dto;
    }

    public static Agendamento toEntity(AgendamentoCriacaoDto dto, Cliente cliente, Barbeiro barbeiro) {
        if (dto == null) return null;
        Agendamento entity = new Agendamento();
        entity.setServico(dto.getServicos());
        entity.setCliente(cliente);
        entity.setBarbeiro(barbeiro);
        entity.setDataAgendamento(dto.getDataAgendamneto());

        return entity;
    }
}
