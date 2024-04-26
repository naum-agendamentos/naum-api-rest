package school.sptech.naumspringapi.dto.loginAdmDto;

import lombok.Data;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Data
public class LoginAdmAtualizarSenhaDto {
    @Size(min = 8, message = "A senha deve conter pelo menos 8 caracteres")
    @NotNull
    @NotBlank
    private String novaSenha;
}
