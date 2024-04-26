package school.sptech.naumspringapi.dto.servicoDto;

import lombok.Data;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;

@Data
public class ServicoListagemDto {
    private int id;
    private String nomeServico;
    private Double preco;
    private BarbeariaListagemDto barbearia;
}
