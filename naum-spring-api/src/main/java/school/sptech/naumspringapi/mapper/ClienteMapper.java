package school.sptech.naumspringapi.mapper;

import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroCriacaoDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;
import school.sptech.naumspringapi.dto.clienteDto.ClienteCriacaoDto;
import school.sptech.naumspringapi.dto.clienteDto.ClienteListagemDto;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Cliente;

import java.util.List;

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
