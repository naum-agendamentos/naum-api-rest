package school.sptech.naumspringapi.dto.enderecoDto;

import lombok.Data;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Data
public class EnderecoAtualizacaoDto {
    @NotNull(message = "(Obrigatório) A 'cidade' do endereço não pode ser nula.")
    @NotBlank(message = "A 'cidade' do endereço não pode estar em branco.")
    private String cidade;
    @NotNull(message = "(Obrigatório) O 'CEP' do endereço não pode ser nulo.")
    @NotBlank(message = "O 'CEP' do endereço não pode estar em branco.")
    @Size(min = 8, max = 8, message = "O tamanho do 'CEP' deve ser exatamente 8.")
    private String cep;
    @NotNull(message = "(Obrigatório) O 'número' do endereço não pode ser nulo.")
    @NotBlank(message = "O 'número' do endereço não pode estar em branco.")
    private String numero;
    @NotNull(message = "(Obrigatório) O 'bairro' do endereço não pode ser nulo.")
    @NotBlank(message = "O 'CEP' do endereço não pode estar em branco.")
    private String bairro;
    @NotNull(message = "(Obrigatório) A 'UF' do endereço não pode ser nula.")
    @NotBlank(message = "O 'UF' do endereço não pode estar em branco.")
    @Size(min = 2, max = 2, message = "O tamanho da 'UF' deve ser exatamente 2.")
    private String uf;
    @NotNull(message = "(Obrigatório) A 'rua' do endereço não pode ser nula.")
    @NotBlank(message = "O 'rua' do endereço não pode estar em branco.")
    private String rua;
}
