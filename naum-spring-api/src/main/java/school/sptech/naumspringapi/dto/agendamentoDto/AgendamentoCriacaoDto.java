package school.sptech.naumspringapi.dto.agendamentoDto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.entity.Servico;

import java.time.LocalDate;
import java.util.List;

@Data
public class AgendamentoCriacaoDto {
    @FutureOrPresent
    @NotNull
    private LocalDate dataAgendamneto;
    @NotNull
    private List<Servico> servicos;
}
