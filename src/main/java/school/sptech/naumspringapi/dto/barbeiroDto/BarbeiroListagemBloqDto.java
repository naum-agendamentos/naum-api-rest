package school.sptech.naumspringapi.dto.barbeiroDto;

import jakarta.validation.constraints.*;
import lombok.Data;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.dto.semanaDto.SemanaCriacaoDto;
import school.sptech.naumspringapi.dto.semanaDto.SemanaListagemDto;

@Data
public class BarbeiroListagemBloqDto {
    private Long id;
    @NotNull(message = "(Obrigatório) O 'nome' do barbeiro não pode ser nulo.")
    @NotBlank(message = "O 'nome' do barbeiro não pode estar em branco.")
    private String nome;
    @Email(message = "O 'email' do barbeiro deve ser válido.")
    @NotNull(message = "(Obrigatório) O 'email' do barbeiro não pode ser nulo.")
    @NotBlank(message = "O 'email' do barbeiro não pode estar em branco.")
    private String email;
    @NotNull(message = "(Obrigatório) A 'senha' do barbeiro não pode ser nula.")
    @NotBlank(message = "A 'senha' do barbeiro não pode estar em branco.")
    @Size(min = 6, message = "A 'senha' deve conter no mínimo 6 caracteres.")
    private String senha;
    @NotNull(message = "(Obrigatório) O 'telefone' do barbeiro não pode ser nulo.")
    @NotBlank(message = "O 'telefone' do barbeiro não pode estar em branco.")
    @Pattern(regexp="\\d{11}", message="Número de telefone inválido.")
    private String telefone;
    @NotNull(message = "(Obrigatório) A 'descrição' do barbeiro não pode ser nula.")
    @NotBlank(message   = "A 'descrição' do barbeiro não pode estar em branco.")
    private String descricao;
    private String foto;
    private BarbeariaListagemDto barbearia;
    private SemanaListagemDto semana;
}
