package school.sptech.naumspringapi.dto.barbeariaDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import school.sptech.naumspringapi.entity.Endereco;

@Data
public class BarbeariaAtualizacaoDto {
    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    @NotBlank
    private String fotoBarbearia;
    @NotNull
    @NotBlank
    private String linkBarbearia;
    @NotNull
    @NotBlank
    private boolean ativa;
}
