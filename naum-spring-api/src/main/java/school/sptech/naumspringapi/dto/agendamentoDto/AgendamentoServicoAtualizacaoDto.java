package school.sptech.naumspringapi.dto.agendamentoDto;

import lombok.Data;
import school.sptech.naumspringapi.entity.Servico;

@Data
public class AgendamentoServicoAtualizacaoDto {
    private Long idAgendamentoServico;
    private Long agendamentoId;
    private Long servicoId;
}
