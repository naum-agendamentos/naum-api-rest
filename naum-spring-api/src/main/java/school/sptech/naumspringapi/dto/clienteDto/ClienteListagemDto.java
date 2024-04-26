package school.sptech.naumspringapi.dto.clienteDto;

import lombok.Data;

@Data
public class ClienteListagemDto {
    private Integer id;
    private String nome;
    private String email;
    private String telefone;
}
