package school.sptech.naumspringapi.dto.servicoDto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServicoCriacaoDto {
    @NotNull(message = "(Obrigatório) O 'preço' do serviço não pode ser nulo")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
    private Double preco;
    @NotNull(message = "(Obrigatório) O 'nome' do serviço não pode ser nulo")
    @NotBlank(message = "O 'nome' do serviço não pode estar em branco")
    private String nomeServico;
}
