package school.sptech.naumspringapi.dto.barbeiroDto;

import lombok.Data;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;

@Data
public class BarbeiroListagemDto {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String descricao;
    private boolean barbeiroAtivo;
    private String foto;
    private BarbeariaListagemDto barbearia;
    private int fkPermissao;
}
