package school.sptech.naumspringapi.dto.barbeariaDto;

import lombok.Data;
import school.sptech.naumspringapi.dto.enderecoDto.EnderecoListagemDto;

@Data
public class BarbeariaListagemDto {
    private Long id;
    private String nome;
    private String linkBarbearia;
    private String fotoBarbearia;
    private EnderecoListagemDto endereco;
}
