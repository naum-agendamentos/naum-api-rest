package school.sptech.naumspringapi.dto.loginAdmDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginAdmCriacaoDto {
    @Email
    @NotNull
    @NotBlank
    private String email;
    @Size(min = 6)
    @NotNull
    @NotBlank
    private String senha;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
