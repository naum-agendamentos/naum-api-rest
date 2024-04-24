package school.sptech.naumspringapi.dto.agendamentoDto;

import lombok.Data;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;

import java.time.LocalDate;
import java.util.List;

@Data
public class AgendamentoListagemDto {
    private Integer id;
    private LocalDate dataAgendamento;
    private ClienteListagemDto cliente;
    private BarbeiroListagemDto barbeiro;
    private List<ServicoListagemDto> servicos;
}
