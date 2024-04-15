package school.sptech.naumspringapi.dto.loginAdmDto;

import jakarta.persistence.ForeignKey;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginAdmAtualizarSenhaDto {
    @Size(min = 8)
    @NotNull
    @NotBlank
    private String novaSenha;



    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }
}
