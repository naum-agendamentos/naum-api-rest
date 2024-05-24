package school.sptech.naumspringapi.dto.agendamentoDto;

import lombok.Data;
import school.sptech.naumspringapi.dto.clienteDto.ClienteListagemDto;
import school.sptech.naumspringapi.dto.servicoDto.ServicoListagemDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class AgendamentoListagemDto {
    private Long id;
    private LocalDate dataAgendamento;
    private LocalTime horaAgendamento;
    private Integer duracaoServico;
    private ClienteListagemDto cliente;
    private BarbeiroListagemDto barbeiro;
    private List<ServicoListagemDto> servicos;
}
