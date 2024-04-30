package school.sptech.naumspringapi.dto.barbeariaDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import school.sptech.naumspringapi.entity.Endereco;

@Data
public class BarbeariaAtualizacaoDto {
    @NotNull
    @NotBlank
    private boolean ativa;
    @NotNull(message = "(Obrigatório) O 'nome' da barbearia não pode ser nula")
    @NotBlank(message = "O 'nome' da barbearia não pode estar em branco")
    private String nome;
    @URL(message = "A URL deve ser válida")
    @NotBlank(message = "O 'linkBarbearia' da barbearia não pode estar em branco")
    @NotNull(message = "(Obrigatório) O 'linkBarbearia' da barbearia não pode ser nulo")
    private String linkBarbearia;
    @NotBlank(message = "A 'fotoBarbearia' da barbearia não pode estar em branco")
    @NotNull(message = "(Obrigatório) A 'fotoBarbearia' da barbearia não pode ser nula")
    private String fotoBarbearia;
}
