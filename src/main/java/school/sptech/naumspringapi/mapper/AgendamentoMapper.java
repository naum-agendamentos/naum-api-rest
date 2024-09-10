package school.sptech.naumspringapi.mapper;

import org.springframework.stereotype.Component;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoBloqListagemDto;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Agendamento;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoCriacaoDto;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoListagemDto;
import school.sptech.naumspringapi.entity.Servico;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AgendamentoMapper {

    public static AgendamentoListagemDto toDto(Agendamento entity, List<Servico> servicos) {
        if (Objects.isNull(entity)) return null;
        AgendamentoListagemDto dto = new AgendamentoListagemDto();
        dto.setId(entity.getId());
        dto.setDataHoraAgendamento(entity.getInicio());
        dto.setCliente(ClienteMapper.toClienteAgendamentoDto(entity.getCliente()));
        dto.setBarbeiro(BarbeiroMapper.toBarbeiroAgendadoDto(entity.getBarbeiro()));
        dto.setValorTotal(entity.getValorTotal());
        dto.setServicos(servicos);
        return dto;
    }


    public static AgendamentoListagemDto toDto(Agendamento entity) {
        if (Objects.isNull(entity)) return null;
        AgendamentoListagemDto dto = new AgendamentoListagemDto();
        dto.setId(entity.getId());
        dto.setDataHoraAgendamento(entity.getInicio());
        dto.setBarbeiro(BarbeiroMapper.toBarbeiroAgendadoDto(entity.getBarbeiro()));
        dto.setValorTotal(entity.getValorTotal());
        return dto;
    }

    public static AgendamentoBloqListagemDto toDtoBloq(Agendamento entity,List<Servico>servicos) {
        if (Objects.isNull(entity)) return null;
        AgendamentoBloqListagemDto dto = new AgendamentoBloqListagemDto();
        dto.setId(entity.getId());
        dto.setDataHoraAgendamentoInicio(entity.getInicio());
        dto.setDataHoraAgendamentoFim(entity.getFim());
        dto.setBarbeiro(BarbeiroMapper.toBarbeiroAgendadoDto(entity.getBarbeiro()));
        dto.setCliente(ClienteMapper.toClienteAgendamentoDto(entity.getCliente()));
        dto.setValorTotal(null);
        dto.setServicos(servicos);
        return dto;
    }

    public static Agendamento toEntity(AgendamentoCriacaoDto dto, Cliente cliente, Barbeiro barbeiro) {
        if (Objects.isNull(dto)) return null;
        Agendamento entity = new Agendamento();
        entity.setInicio(dto.getDataHoraAgendamneto());
        entity.setFim(dto.getDataHoraAgendamneto());
        entity.setCliente(cliente);
        entity.setBarbeiro(barbeiro);
        return entity;
    }


    public static List<AgendamentoListagemDto> toDto(List<Agendamento> entities, List<Servico> servicos) {
        return entities.stream()
                .map(entity -> AgendamentoMapper.toDto(entity, servicos))
                .collect(Collectors.toList());
    }
}
