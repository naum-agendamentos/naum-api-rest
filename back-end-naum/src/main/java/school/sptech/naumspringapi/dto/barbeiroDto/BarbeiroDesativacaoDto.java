package school.sptech.naumspringapi.dto.barbeiroDto;

import lombok.Data;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;

@Data
public class BarbeiroDesativacaoDto {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String foto;
    private BarbeariaListagemDto barbearia;
    private boolean barbeiroAtivo;
}
