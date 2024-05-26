package school.sptech.naumspringapi.mapper;

import org.springframework.stereotype.Component;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoListagemDto;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.dto.clienteDto.ClienteCriacaoDto;
import school.sptech.naumspringapi.dto.clienteDto.ClienteListagemDto;

import java.util.List;
import java.util.Objects;

@Component
public class ClienteMapper {

    public static Cliente toEntity(ClienteCriacaoDto dto) {
        if (Objects.isNull(dto)) return null;

        Cliente cliente = new Cliente();
        cliente.setEmail(dto.getEmail());
        cliente.setNome(dto.getNome());
        cliente.setTelefone(dto.getTelefone());
        cliente.setSenha(dto.getSenha());

        return cliente;
    }

    public static ClienteListagemDto toDto(Cliente entity) {
        if (Objects.isNull(entity)) return null;

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

    public static AgendamentoListagemDto.ClienteListagemDto toClienteAgendamentoDto(Cliente entity) {
        if (Objects.isNull(entity)) return null;
        AgendamentoListagemDto.ClienteListagemDto dto = new AgendamentoListagemDto.ClienteListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        return dto;
    }
}
