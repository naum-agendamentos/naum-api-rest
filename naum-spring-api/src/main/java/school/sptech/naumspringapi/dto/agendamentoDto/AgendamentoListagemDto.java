package school.sptech.naumspringapi.dto.agendamentoDto;

import lombok.Data;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;
import school.sptech.naumspringapi.dto.clienteDto.ClienteListagemDto;
import school.sptech.naumspringapi.dto.servicoDto.ServicoListagemDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AgendamentoListagemDto {
    private Long id;
    private LocalDateTime dataHoraAgendamento;
    private ClienteListagemDto cliente;
    private BarbeiroListagemDto barbeiro;
    private List<ServicoListagemDto> servicos;
}
