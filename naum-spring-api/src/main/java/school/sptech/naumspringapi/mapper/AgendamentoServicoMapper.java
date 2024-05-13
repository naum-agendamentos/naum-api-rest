package school.sptech.naumspringapi.mapper;

import org.springframework.stereotype.Component;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoServicoCriacaoDto;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoServicoListagemDto;
import school.sptech.naumspringapi.entity.AgendamentoServico;

import java.util.List;
import java.util.Objects;

@Component
public class AgendamentoServicoMapper {

    public static AgendamentoServico toEntity(AgendamentoServicoCriacaoDto dto) {
        if (Objects.isNull(dto)) return null;
        AgendamentoServico entity = new AgendamentoServico();
        entity.setServico(dto.getServico());
        entity.setAgendamento(dto.getAgendamento());
        return entity;
    }

    public static AgendamentoServicoListagemDto toDto(AgendamentoServico entity) {
        if (Objects.isNull(entity)) return null;
        AgendamentoServicoListagemDto dto = new AgendamentoServicoListagemDto();
        dto.setIdAgendamentoServico(entity.getIdAgendamentoServico());
        dto.setAgendamentoListagemDto(AgendamentoMapper.toDto(entity.getAgendamento()));
        dto.setServicoListagemDto(ServicoMapper.toDto(entity.getServico()));
        return dto;
    }

    public static List<AgendamentoServicoListagemDto> toDto(List<AgendamentoServico> entities) {
        return entities.stream().map(AgendamentoServicoMapper::toDto).toList();
    }
}
