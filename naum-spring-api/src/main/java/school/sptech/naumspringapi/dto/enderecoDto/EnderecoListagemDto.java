package school.sptech.naumspringapi.dto.enderecoDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoListagemDto {
    private Long id;
    private String cidade;
    private String cep;
    private String numero;
    private String bairro;
    private String uf;
    private String rua;

}
