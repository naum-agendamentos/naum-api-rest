package school.sptech.naumspringapi.dto.barbeiroDto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.entity.Barbearia;

@Data
public class BarbeiroCriacaoDto {
    @NotNull
    @NotBlank
    @Size(min = 3, max = 30)
    private String nome;

    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 20)
    private String senha;

    @Pattern(regexp="\\d{11}", message="Número de telefone inválido")
    private String telefone;

    @NotNull
    @NotBlank
    @Size(min = 10, max = 100)
    private String descricao;

    @NotNull
    @NotBlank
    private String foto;
}
