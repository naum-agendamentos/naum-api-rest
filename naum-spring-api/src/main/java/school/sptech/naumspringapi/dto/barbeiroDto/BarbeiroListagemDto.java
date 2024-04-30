package school.sptech.naumspringapi.dto.barbeiroDto;

import jakarta.persistence.*;
import lombok.Data;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.entity.Barbearia;

@Data
public class BarbeiroListagemDto {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String foto;
    private BarbeariaListagemDto barbearia;
}
