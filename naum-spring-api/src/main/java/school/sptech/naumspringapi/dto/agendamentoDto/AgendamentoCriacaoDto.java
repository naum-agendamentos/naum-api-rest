package school.sptech.naumspringapi.dto.agendamentoDto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import school.sptech.naumspringapi.entity.Servico;
import jakarta.validation.constraints.FutureOrPresent;

import java.util.List;
import java.time.LocalDate;

@Data
public class AgendamentoCriacaoDto {
    @FutureOrPresent(message = "A data do agendamento deve ser presente ou futura")
    @NotNull(message = "(Obrigatório) A 'data' do agendamento não pode estar nula")
    private LocalDate dataAgendamneto;
    @NotNull(message = "(Obrigatório) O 'serviço' do agendamento não pode ser nulo")
    @NotEmpty(message = "O agendamento deve conter pelo menos um 'serviço'")
    private List<Servico> servicos;
}
