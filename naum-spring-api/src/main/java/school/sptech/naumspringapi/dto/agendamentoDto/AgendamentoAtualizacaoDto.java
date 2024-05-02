package school.sptech.naumspringapi.dto.agendamentoDto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import school.sptech.naumspringapi.entity.Servico;
import jakarta.validation.constraints.FutureOrPresent;

import java.util.List;
import java.time.LocalDateTime;

@Data
public class AgendamentoAtualizacaoDto {
    @FutureOrPresent(message = "A data do agendamento deve ser presente ou futura.")
    @NotNull(message = "(Obrigatório) A 'data' do agendamento não pode estar nula.")
    private LocalDateTime dataHoraAgendamneto;
    @NotNull(message = "(Obrigatório) O 'serviço' do agendamento não pode ser nulo.")
    @NotEmpty(message = "O agendamento deve conter pelo menos um 'serviço'.")
    private List<Servico> servicos;
}
