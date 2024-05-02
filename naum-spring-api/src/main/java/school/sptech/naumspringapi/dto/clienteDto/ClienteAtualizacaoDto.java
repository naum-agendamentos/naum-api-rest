package school.sptech.naumspringapi.dto.clienteDto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class ClienteAtualizacaoDto {
    @NotNull(message = "(Obrigatório) O 'email' do cliente não pode ser nulo.")
    @NotBlank(message = "O 'email' do cliente não pode estar em branco.")
    @Email(message = "O 'email' deve ser válido.")
    private String email;
    @NotNull(message = "(Obrigatório) O 'nome' do cliente não pode ser nulo.")
    @NotBlank(message = "O 'nome' do cliente não pode estar em branco.")
    private String nome;
    @NotNull(message = "(Obrigatório) O 'telefone' do cliente não pode ser nulo.")
    @NotBlank(message = "O 'telefone' do cliente não pode estar em branco.")
    @Pattern(regexp="\\d{11}", message="Número de telefone inválido.")
    private String telefone;
    @NotNull(message = "(Obrigatório) A 'senha' do cliente não pode ser nula.")
    @NotBlank(message = "A 'senha' do cliente não pode estar em branco.")
    @Size(min = 6, message = "O tamnaho da 'senha' deve ser de no mínimo 6.")
    private String senha;
}
