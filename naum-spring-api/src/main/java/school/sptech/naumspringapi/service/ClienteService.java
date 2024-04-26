package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.mapper.ClienteMapper;
import school.sptech.naumspringapi.repository.ClienteRepository;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.dto.clienteDto.ClienteCriacaoDto;
import school.sptech.naumspringapi.dto.clienteDto.ClienteListagemDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Transactional
    public ClienteListagemDto criarCliente(ClienteCriacaoDto clienteDto) {
        if (clienteDto == null) return null;
        Cliente clienteSalvo = clienteRepository.save(ClienteMapper.toEntity(clienteDto));
        return ClienteMapper.toDto(clienteSalvo);
    }

    public List<ClienteListagemDto> listarClientes() {
        return ClienteMapper.toDto(clienteRepository.findAll());
    }
 }
