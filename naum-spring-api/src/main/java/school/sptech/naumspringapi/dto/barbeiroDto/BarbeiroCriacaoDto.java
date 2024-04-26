package school.sptech.naumspringapi.dto.barbeiroDto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Data
public class BarbeiroCriacaoDto {
    @NotNull(message = "(Obrigatório) O 'nome' do barbeiro não pode ser nulo")
    @NotBlank(message = "O 'nome' do barbeiro não pode estar em branco")
    private String nome;
    @Email(message = "O 'email' do barbeiro deve ser válido")
    @NotNull(message = "(Obrigatório) O 'email' do barbeiro não pode ser nulo")
    @NotBlank(message = "O 'email' do barbeiro não pode estar em branco")
    private String email;
    @NotNull(message = "(Obrigatório) A 'senha' do barbeiro não pode ser nula")
    @NotBlank(message = "A 'senha' do barbeiro não pode estar em branco")
    private String senha;
    @NotNull(message = "(Obrigatório) O 'telefone' do barbeiro não pode ser nulo")
    @NotBlank(message = "O 'telefone' do barbeiro não pode estar em branco")
    private String telefone;
    @NotNull(message = "(Obrigatório) A 'descrição' do barbeiro não pode ser nula")
    @NotBlank(message = "A 'descrição' do barbeiro não pode estar em branco")
    private String descricao;
    @NotNull(message = "(Obrigatório) O 'barbeiroAtivo' do barbeiro não pode ser nulo")
    private boolean barbeiroAtivo;
    @NotNull(message = "(Obrigatório) A 'foto' do barbeiro não pode ser nula")
    @NotBlank(message = "A 'foto' do barbeiro não pode estar em branco")
    private String foto;
    @NotNull(message = "(Obrigatório) O barbeiro deve ter alguma permissão atribuída, e não pode ser nula")
    private int fkPermissao;
}
