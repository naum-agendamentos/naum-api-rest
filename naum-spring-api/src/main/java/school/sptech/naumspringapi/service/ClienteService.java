package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.Cliente;
import jakarta.persistence.EntityNotFoundException;
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
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioService usuarioService;

    @Transactional
    public ClienteListagemDto criar(ClienteCriacaoDto novoCliente) {
        try {
            if (Objects.isNull(novoCliente)) throw new BadRequestException();
            Cliente entity = ClienteMapper.toEntity(novoCliente);

            UsuarioCriacaoDto usuarioCriacaoDto = new UsuarioCriacaoDto();
            usuarioCriacaoDto.setNome(novoCliente.getNome());
            usuarioCriacaoDto.setEmail(novoCliente.getEmail());
            usuarioCriacaoDto.setSenha(novoCliente.getSenha());
            usuarioCriacaoDto.setTipo(UsuarioTipo.CLIENTE);
            Usuario usuarioCriado = usuarioService.criar(usuarioCriacaoDto);

            entity.setUsuario(usuarioCriado);

            return ClienteMapper.toDto(clienteRepository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar cliente", e);
        }
    }

    public Cliente buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (Objects.isNull(cliente)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada");
        return cliente;
    }

    public List<ClienteListagemDto> listarClientes() {
        try {
            List<Cliente> clientes = clienteRepository.findAll();
            if (clientes.isEmpty()) return null;
            return ClienteMapper.toDto(clientes);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar clientes", e);
        }
    }

    public ClienteListagemDto atualizarCliente(Long id, ClienteCriacaoDto clienteAtualizado) {
        try {
            if (Objects.isNull(id) || Objects.isNull(clienteAtualizado)) throw new BadRequestException();
            Cliente clienteAtual = clienteRepository.findById(id).orElse(null);
            if (Objects.isNull(clienteAtual)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada");

            Cliente entity = ClienteMapper.toEntity(clienteAtualizado);
            entity.setId(clienteAtual.getId());

            UsuarioCriacaoDto usuarioCriacaoDto = new UsuarioCriacaoDto();
            usuarioCriacaoDto.setNome(clienteAtualizado.getNome());
            usuarioCriacaoDto.setEmail(clienteAtualizado.getEmail());
            usuarioCriacaoDto.setSenha(clienteAtualizado.getSenha());
            usuarioCriacaoDto.setTipo(UsuarioTipo.CLIENTE);

            Usuario usuarioAtualizado = usuarioService.atualizar(clienteAtual.getId(), usuarioCriacaoDto);

            if (Objects.isNull(usuarioAtualizado)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi achado um login desse cliente");

            entity.setUsuario(usuarioAtualizado);
            return ClienteMapper.toDto(clienteRepository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar cliente", e);
        }
    }

    @Transactional
    public void excluirCliente(Long idCliente) {
        try {
            clienteRepository.deleteById(idCliente);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir cliente", e);
        }
    }

    public ClienteListagemDto buscarClientePorIdDto(Long idCliente) {
        ClienteListagemDto clienteListagemDto = ClienteMapper.toDto(clienteRepository.findById(idCliente).orElse(null));
        if (Objects.isNull(clienteListagemDto)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada");
        return clienteListagemDto;
    }
}
