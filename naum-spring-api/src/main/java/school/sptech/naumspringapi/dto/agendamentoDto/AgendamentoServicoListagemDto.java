package school.sptech.naumspringapi.dto.agendamentoDto;

import lombok.Data;
import school.sptech.naumspringapi.dto.servicoDto.ServicoListagemDto;

@Data
public class AgendamentoServicoListagemDto {
    private Long idAgendamentoServico;
    private AgendamentoListagemDto agendamentoListagemDto;
    private ServicoListagemDto servicoListagemDto;
}
