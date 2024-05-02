package school.sptech.naumspringapi.dto.loginAdmDto;

import lombok.Data;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Data
public class LoginAdmAtualizacaoDto {
    @Email(message = "O 'email' deve ser válido.")
    private String email;
    @NotNull(message = "A 'senha' não pode ser nula.")
    @NotBlank(message = "A 'senha' não pode estar em branco.")
    @Size(min = 6, message = "A 'senha' deve conter pelo menos 6 caracteres.")
    private String senha;
}
