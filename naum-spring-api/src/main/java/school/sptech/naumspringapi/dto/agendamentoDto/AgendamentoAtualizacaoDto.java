package school.sptech.naumspringapi.dto.agendamentoDto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import school.sptech.naumspringapi.dto.servicoDto.ServicoListagemDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class AgendamentoAtualizacaoDto {
    @FutureOrPresent(message = "A 'data' do agendamento deve ser presente ou futura.")
    @NotNull(message = "(Obrigatório) A 'data' do agendamento não pode estar nula.")
    private LocalDate dataAgendamneto;
    @FutureOrPresent(message = "A 'hora' do agendamento deve ser presente ou futura.")
    @NotNull(message = "(Obrigatório) A 'hora' do agendamento não pode estar nula.")
    private LocalTime horaAgendamneto;
    private List<Long> servicos;
}
