package school.sptech.naumspringapi.dto.agendamentoDto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import school.sptech.naumspringapi.entity.Servico;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AgendamentoCriacaoDto {
    @FutureOrPresent(message = "A data do agendamento deve ser presente ou futura")
    @NotNull(message = "(Obrigatório) A 'data' do agendamento não pode estar nula")
    private LocalDateTime dataHoraAgendamneto;
    @NotNull(message = "(Obrigatório) O 'serviço' do agendamento não pode ser nulo")
    @NotEmpty(message = "O agendamento deve conter pelo menos um 'serviço'")
    private List<Servico> servicos;
}
