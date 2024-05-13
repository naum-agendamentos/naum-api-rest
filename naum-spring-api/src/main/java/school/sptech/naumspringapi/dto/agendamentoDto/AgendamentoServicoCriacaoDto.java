package school.sptech.naumspringapi.dto.agendamentoDto;

import lombok.Data;
import school.sptech.naumspringapi.entity.Servico;
import school.sptech.naumspringapi.entity.Agendamento;

@Data
public class AgendamentoServicoCriacaoDto {
    private Long idAgendamentoServico;
    private Agendamento agendamento;
    private Servico servico;
}
