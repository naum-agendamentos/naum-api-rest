package school.sptech.naumspringapi.dto.barbeariaDto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import school.sptech.naumspringapi.dto.enderecoDto.EnderecoCriacaoDto;
import school.sptech.naumspringapi.entity.Endereco;

@Data
public class BarbeariaCriacaoDto {
    @NotNull
    @NotBlank
    private String nome;
    @URL
    @NotNull
    @NotBlank
    private String linkBarbearia;
    @NotNull
    @NotBlank
    private String fotoBarbearia;
    @NotNull
    private EnderecoCriacaoDto endereco;
}
