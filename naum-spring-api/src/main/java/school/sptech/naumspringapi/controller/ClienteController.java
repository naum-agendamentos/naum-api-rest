package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.mapper.ClienteMapper;
import school.sptech.naumspringapi.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public <ClienteDto> ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody @Valid ClienteCadastroDTO novoCliente) {
        if (clienteRepository.existsByEmailCliente(novoCliente.getEmailCliente())) {
            throw new EmailJaCadastradoException(novoCliente.getEmailCliente());
        }

        novoCliente.setSenhaCliente(criptografarSenha(novoCliente.getSenhaCliente())); // Criptografe a senha
        Cliente cliente = ClienteMapper.toEntity(novoCliente);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        ClienteDTO clienteDTO = ClienteMapper.toDTO(clienteSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO); // Retorne o status 201 Criado
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarClientes() {
        List<Cliente> lista = clienteRepository.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Retorne o status 204 sem conteúdo
        }

        List<ClienteDTO> listaDTO = ClienteMapper.toDTO(lista);
        return ResponseEntity.status(HttpStatus.OK).body(listaDTO); // Retorne o status 200 OK
    }

    @GetMapping("/buscar/nome/{nome}")
    public ResponseEntity<ClienteDTO> buscarClientePorNome(@PathVariable String nomeCliente) {
        List<Cliente> clientesEncontrados = clienteRepository.findByNomeClienteContainingIgnoreCase(nomeCliente);

        if (clientesEncontrados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorne o status 404 Não encontrado
        }

        Cliente cliente = clientesEncontrados.get(0); // Assumindo que se encontrou apenas um cliente
        ClienteDTO clienteDTO = ClienteMapper.toDTO(cliente);

        return ResponseEntity.status(HttpStatus.OK).body(clienteDTO); // Retorne o status 200 OK
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ClienteDto> atualizarCliente(@PathVariable Long idCliente, @RequestBody @Valid ClienteCadastroDTO clienteAtualizadoDTO) {
        Optional<Cliente> clienteOptional = clienteRepository.findByIdCliente(idCliente);

        if (clienteOptional.isEmpty()) {
            throw new ClienteNotFoundException(idCliente);
        }

        Cliente cliente = clienteOptional.get();
        ClienteMapper.toEntityUpdate(clienteAtualizadoDTO, cliente);

        Cliente clienteAtualizado = clienteRepository.save(cliente);
        ClienteDTO clienteDTO = ClienteMapper.toDTO(clienteAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(clienteDTO); // Retorne o status 200 OK
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long idCliente) {
        clienteRepository.deleteByIdCliente(idCliente);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Retorne o status 204 sem conteúdo
    }
}
