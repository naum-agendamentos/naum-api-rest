package school.sptech.naumspringapi.dto.agendamentoDto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class AgendamentoCriacaoDto {
    @FutureOrPresent(message = "A data do agendamento deve ser presente ou futura.")
    @NotNull(message = "(Obrigatório) A 'data' do agendamento não pode estar nula.")
    private LocalDate dataAgendamneto;
    @FutureOrPresent(message = "O 'horario' do agendamento deve ser presente ou futuro.")
    @NotNull(message = "(Obrigatório) O 'horario' do agendamento não pode estar nulo.")
    private LocalTime horaAgendamento;
}
