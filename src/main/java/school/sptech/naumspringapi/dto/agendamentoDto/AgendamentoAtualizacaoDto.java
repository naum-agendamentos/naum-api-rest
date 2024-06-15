package school.sptech.naumspringapi.dto.agendamentoDto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDateTime;

@Data
public class AgendamentoAtualizacaoDto {
    @FutureOrPresent(message = "A data do agendamento deve ser presente ou futura.")
    @NotNull(message = "(Obrigatório) A 'data' do agendamento não pode estar nula.")
    private LocalDateTime dataHoraAgendamneto;
}
