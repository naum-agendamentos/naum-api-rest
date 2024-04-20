package school.sptech.naumspringapi.dto.enderecoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnderecoCriacaoDto {
    @NotNull
    @NotBlank
    private String cidade;
    @NotNull
    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;
    @NotNull
    private String numero;
    @NotNull
    @NotBlank
    private String bairro;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 2)
    private String uf;
    @NotNull
    @NotBlank
    private String rua;
}
