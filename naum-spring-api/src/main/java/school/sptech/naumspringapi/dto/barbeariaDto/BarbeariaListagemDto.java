package school.sptech.naumspringapi.dto.barbeariaDto;

import lombok.Data;
import school.sptech.naumspringapi.dto.enderecoDto.EnderecoListagemDto;
import school.sptech.naumspringapi.entity.Endereco;

@Data
public class BarbeariaListagemDto {
    private int id;
    private String nome;
    private String linkBarbearia;
    private String fotoBarbearia;
    private EnderecoListagemDto endereco;
}
