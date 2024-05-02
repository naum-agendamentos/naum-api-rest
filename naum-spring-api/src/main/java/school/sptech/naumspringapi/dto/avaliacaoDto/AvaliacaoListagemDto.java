package school.sptech.naumspringapi.dto.avaliacaoDto;

import lombok.Data;
import school.sptech.naumspringapi.dto.clienteDto.ClienteListagemDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;

@Data
public class AvaliacaoListagemDto {
    private Long id;
    private Integer qtdEstrela;
    private ClienteListagemDto cliente;
    private BarbeariaListagemDto barbearia;
}
