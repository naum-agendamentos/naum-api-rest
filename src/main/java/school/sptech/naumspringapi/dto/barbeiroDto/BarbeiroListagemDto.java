package school.sptech.naumspringapi.dto.barbeiroDto;

import lombok.Data;
import school.sptech.naumspringapi.dto.semanaDto.SemanaListagemDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;

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
