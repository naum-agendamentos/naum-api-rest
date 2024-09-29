package school.sptech.naumspringapi.dto.barbeiroDto;

import lombok.Data;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.dto.semanaDto.SemanaListagemDto;

@Data
public class BarbeiroListagemDto {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String descricao;
    private String foto;
    private BarbeariaListagemDto barbearia;
    private SemanaListagemDto semana;
}
