package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.mapper.ClienteMapper;
import school.sptech.naumspringapi.domain.usuario.Usuario;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.naumspringapi.domain.usuario.UsuarioTipo;
import school.sptech.naumspringapi.repository.ClienteRepository;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.service.usuario.UsuarioService;
import school.sptech.naumspringapi.dto.clienteDto.ClienteCriacaoDto;
import school.sptech.naumspringapi.dto.clienteDto.ClienteListagemDto;
import school.sptech.naumspringapi.service.usuario.dto.UsuarioCriacaoDto;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioService usuarioService;

    @Transactional
    public Cliente criar(ClienteCriacaoDto novoCliente) {

        Cliente entity = ClienteMapper.toEntity(novoCliente);

        UsuarioCriacaoDto usuarioCriacaoDto = new UsuarioCriacaoDto();
        usuarioCriacaoDto.setNome(novoCliente.getNome());
        usuarioCriacaoDto.setEmail(novoCliente.getEmail());
        usuarioCriacaoDto.setSenha(novoCliente.getSenha());
        usuarioCriacaoDto.setTipo(UsuarioTipo.CLIENTE);
        Usuario usuarioCriado = usuarioService.criar(usuarioCriacaoDto);

        entity.setUsuario(usuarioCriado);

        return clienteRepository.save(entity);
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) return null;
        return clientes;
    }

    public Cliente atualizarCliente(Long id, ClienteCriacaoDto clienteAtualizado) {
        Optional<Cliente> clienteAtualOpt = clienteRepository.findById(id);

        if (clienteAtualOpt.isEmpty()){
            return null;
        }

        Cliente entity = ClienteMapper.toEntity(clienteAtualizado);
        entity.setId(clienteAtualOpt.get().getId());

        UsuarioCriacaoDto usuarioCriacaoDto = new UsuarioCriacaoDto();
        usuarioCriacaoDto.setNome(clienteAtualizado.getNome());
        usuarioCriacaoDto.setEmail(clienteAtualizado.getEmail());
        usuarioCriacaoDto.setSenha(clienteAtualizado.getSenha());
        usuarioCriacaoDto.setTipo(UsuarioTipo.CLIENTE);

        Usuario usuarioAtualizado = usuarioService.atualizar(clienteAtualOpt.get().getId(), usuarioCriacaoDto);

        if(usuarioAtualizado == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi achado um login desse cliente");
        }

        entity.setUsuario(usuarioAtualizado);

        Cliente clienteSalvo = clienteRepository.save(entity);

        return clienteSalvo;
    }

    @Transactional
    public Void excluirCliente(Long idCliente) {
        clienteRepository.deleteById(idCliente);
        return null;
    }

    public ClienteListagemDto buscarClientePorIdDto(Long idCliente) {
        return ClienteMapper.toDto(clienteRepository.findById(idCliente).orElseThrow());
    }
}
