package school.sptech.naumspringapi.mapper;

import org.springframework.stereotype.Component;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.dto.clienteDto.ClienteCriacaoDto;
import school.sptech.naumspringapi.dto.clienteDto.ClienteListagemDto;

import java.util.List;

@Component
public class ClienteMapper {

    public static Cliente toEntity(ClienteCriacaoDto dto) {
        if (dto == null) return null;

        Cliente cliente = new Cliente();
        cliente.setEmail(dto.getEmail());
        cliente.setNome(dto.getNome());
        cliente.setTelefone(dto.getTelefone());
        cliente.setSenha(dto.getSenha());

        return cliente;
    }

    public static ClienteListagemDto toDto(Cliente entity) {
        if (entity == null) return null;

        ClienteListagemDto dto = new ClienteListagemDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setNome(entity.getNome());
        dto.setTelefone(entity.getTelefone());

        return dto;
    }

    public static List<ClienteListagemDto> toDto(List<Cliente> entities) {
        return entities.stream().map(ClienteMapper::toDto).toList();
    }
}
