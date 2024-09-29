package school.sptech.naumspringapi.dto.muralAvisosDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import school.sptech.naumspringapi.entity.TipoAviso;

@Data
public class MuralAvisosCriacaoDto {

    @NotNull(message = "(Obrigatório) O 'titulo' não pode ser nulo.")
    @NotBlank(message = "O 'titulo' não pode estar em branco.")
    private String titulo;

    @Size(max = 500, message = "A 'descricao' pode ter no máximo 500 caracteres.")
    private String descricao;

    @URL(message = "A URL deve ser válida.")
    @Size(max = 1000, message = "A 'url' pode ter no máximo 1000 caracteres.")
    private String url;

    @NotNull(message = "(Obrigatório) A 'urgencia' não pode ser nula.")
    private TipoAviso tipoAviso;

    @NotNull(message = "(Obrigatório) O 'barbeiroId' não pode ser nulo.")
    private Long barbeiroId;
}
