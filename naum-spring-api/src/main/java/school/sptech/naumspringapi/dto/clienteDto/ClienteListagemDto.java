package school.sptech.naumspringapi.dto.clienteDto;

import lombok.Data;

@Data
public class ClienteListagemDto {
    private Long id;
    private String email;
    private String nome;
    private String telefone;
}
