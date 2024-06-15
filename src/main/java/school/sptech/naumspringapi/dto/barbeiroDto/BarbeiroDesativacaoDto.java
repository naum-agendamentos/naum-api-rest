package school.sptech.naumspringapi.dto.barbeiroDto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.AssertFalse;

@Data
public class BarbeiroDesativacaoDto {
    @NotNull(message = "(Obrigatório) 'id' não pode ser nulo.")
    private Long id;
    @AssertFalse(message = "'barbeiroAtivo' deve ser false.")
    @NotNull(message = "(Obrigatório) 'barbeiroAtivo' não pode ser nulo.")
    private Boolean barbeiroAtivo;
}
