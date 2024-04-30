package school.sptech.naumspringapi.dto.avaliacaoDto;

import lombok.Data;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.dto.clienteDto.ClienteListagemDto;

@Data
public class AvaliacaoListagemDto {
    private Long id;
    private int qtdEstrela;
    private ClienteListagemDto cliente;
    private BarbeariaListagemDto barbearia;
}
