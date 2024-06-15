package school.sptech.naumspringapi.dto.avaliacaoDto;

import lombok.Data;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class AvaliacaoCriacaoDto {
    @NotNull(message = "(Obrigatório) A 'qtdEstrela' não pode ser nula.")
    @Min(value = 1, message = "O mínimo de estrelas é '1'.")
    @Max(value = 5, message = "O máximo de estrelas é '5'.")
    private Integer qtdEstrela;
}
