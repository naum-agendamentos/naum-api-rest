package school.sptech.naumspringapi.dto.enderecoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnderecoCriacaoDto {
    @NotNull
    @NotBlank
    private String Cidade;
    @NotNull
    @NotBlank
    private String Rua;
    @NotNull
    @NotBlank
    private String bairro;
    @NotNull
    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;
    @NotNull
    @NotBlank
    private String numero;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 2)
    private String uf;
}
