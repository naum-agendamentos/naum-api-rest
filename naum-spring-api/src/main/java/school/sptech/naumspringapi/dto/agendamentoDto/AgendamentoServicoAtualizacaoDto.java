package school.sptech.naumspringapi.dto.agendamentoDto;

import lombok.Data;
import school.sptech.naumspringapi.entity.Servico;

@Data
public class AgendamentoServicoAtualizacaoDto {
    private Long idAgendamentoServico;
    private AgendamentoAtualizacaoDto agendamentoAtualizacaoDto;
    private Servico servico;
}
