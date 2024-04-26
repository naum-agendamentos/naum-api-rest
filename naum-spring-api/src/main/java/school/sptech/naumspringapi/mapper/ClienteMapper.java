package school.sptech.naumspringapi.mapper;

import lombok.RequiredArgsConstructor;
import school.sptech.naumspringapi.dto.clienteDto.ClienteCriacaoDto;
import school.sptech.naumspringapi.dto.clienteDto.ClienteListagemDto;
import school.sptech.naumspringapi.entity.Cliente;

import java.util.List;

@RequiredArgsConstructor
public class ClienteMapper {

    public static Cliente toEntity(ClienteCriacaoDto dto) {
        if (dto == null) return null;
        Cliente cliente = new Cliente();
        cliente.setEmail(dto.getEmail());
        cliente.setNome(dto.getNome());
        cliente.setSenha(dto.getSenha());
        cliente.setTelefone(dto.getTelefone());
        return cliente;
    }

    public static ClienteListagemDto toDto(Cliente entity) {
        if (entity == null) return null;
        ClienteListagemDto dto = new ClienteListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        return dto;
    }

    public static List<ClienteListagemDto> toDto(List<Cliente> entities) {
        return entities.stream().map(ClienteMapper::toDto).toList();
    }
}
