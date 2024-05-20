package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.mapper.ClienteMapper;
import school.sptech.naumspringapi.domain.usuario.Usuario;
import school.sptech.naumspringapi.domain.usuario.UsuarioTipo;
import school.sptech.naumspringapi.exception.ConflitoException;
import school.sptech.naumspringapi.repository.ClienteRepository;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.service.usuario.UsuarioService;
import school.sptech.naumspringapi.exception.NaoEncontradoException;
import school.sptech.naumspringapi.dto.clienteDto.ClienteCriacaoDto;
import school.sptech.naumspringapi.exception.RequisicaoInvalidaException;
import school.sptech.naumspringapi.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.naumspringapi.exception.EntidadeImprocessavelException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final UsuarioService usuarioService;
    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente criar(ClienteCriacaoDto novoCliente) {
        if (Objects.isNull(novoCliente)) throw new RequisicaoInvalidaException("Cliente");
        if (clienteRepository.existsByEmail(novoCliente.getEmail())) throw new ConflitoException("Email");
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
        return clienteRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Cliente"));
    }

    public Cliente buscarPorUsuario(Long idUsuario) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
        return clienteRepository.findByUsuario(usuario);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente atualizarCliente(Long id, ClienteCriacaoDto clienteAtualizado) {
        if (Objects.isNull(id) || Objects.isNull(clienteAtualizado)) throw new EntidadeImprocessavelException("id ou atualização do cliente");
        Cliente clienteAtual = clienteRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Cliente"));
        Cliente entity = ClienteMapper.toEntity(clienteAtualizado);
        entity.setId(clienteAtual.getId());

        UsuarioCriacaoDto usuarioCriacaoDto = new UsuarioCriacaoDto();
        usuarioCriacaoDto.setNome(clienteAtualizado.getNome());
        usuarioCriacaoDto.setEmail(clienteAtualizado.getEmail());
        usuarioCriacaoDto.setSenha(clienteAtualizado.getSenha());
        usuarioCriacaoDto.setTipo(UsuarioTipo.CLIENTE);

        Usuario usuarioAtualizado = usuarioService.atualizar(clienteAtual.getId(), usuarioCriacaoDto);

        if (Objects.isNull(usuarioAtualizado)) throw new NaoEncontradoException("Login usuário");

        entity.setUsuario(usuarioAtualizado);
        return clienteRepository.save(entity);
    }

    @Transactional
    public void excluirCliente(Long idCliente) {
        if (!clienteRepository.existsById(idCliente)) throw new NaoEncontradoException("Cliente");
        clienteRepository.deleteById(idCliente);
    }
}
