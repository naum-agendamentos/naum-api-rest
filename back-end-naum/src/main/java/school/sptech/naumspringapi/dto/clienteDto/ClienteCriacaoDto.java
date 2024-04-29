package school.sptech.naumspringapi.dto.clienteDto;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.Data;
import school.sptech.naumspringapi.domain.usuario.Usuario;

@Data
public class ClienteCriacaoDto {

    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 30)
    private String nome;

    @Pattern(regexp="\\d{11}", message="Número de telefone inválido")
    private String telefone;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 20)
    private String senha;
}
