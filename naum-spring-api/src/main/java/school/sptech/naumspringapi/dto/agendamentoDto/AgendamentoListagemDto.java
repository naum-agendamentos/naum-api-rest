package school.sptech.naumspringapi.dto.agendamentoDto;

import lombok.Data;
import school.sptech.naumspringapi.dto.clienteDto.ClienteListagemDto;
import school.sptech.naumspringapi.dto.servicoDto.ServicoListagemDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;

import java.util.List;
import java.time.LocalDate;

@Data
public class AgendamentoListagemDto {
    private Integer id;
    private LocalDate dataAgendamento;
    private ClienteListagemDto cliente;
    private BarbeiroListagemDto barbeiro;
    private List<ServicoListagemDto> servicos;
}
