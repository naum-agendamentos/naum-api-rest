package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.mapper.ClienteMapper;
import school.sptech.naumspringapi.service.ClienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import school.sptech.naumspringapi.dto.clienteDto.ClienteCriacaoDto;
import school.sptech.naumspringapi.dto.clienteDto.ClienteListagemDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(summary = "Cadastrar clientes", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<ClienteListagemDto> cadastrar(@RequestBody @Valid ClienteCriacaoDto novoCliente) {
        Cliente clienteCriado = clienteService.criar(novoCliente);
        ClienteListagemDto clienteListagemDto = ClienteMapper.toDto(clienteCriado);
        return ResponseEntity.ok(clienteListagemDto);
    }

    @Operation(summary = "Listar clientes", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<ClienteListagemDto>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        if (clientes == null) return ResponseEntity.noContent().build();
        List<ClienteListagemDto> clienteListagemDtos = ClienteMapper.toDto(clientes);
        return ResponseEntity.ok(clienteListagemDtos);
    }

    @Operation(summary = "Atualizar cliente", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}")
    public ResponseEntity<ClienteListagemDto> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteCriacaoDto novoCliente) {
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, novoCliente);
        if (clienteAtualizado == null) return ResponseEntity.notFound().build();
        ClienteListagemDto clienteListagemDto = ClienteMapper.toDto(clienteAtualizado);
        return ResponseEntity.ok(clienteListagemDto);
    }

    @Operation(summary = "Atualizar cliente", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long idCliente) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.excluirCliente(idCliente));
    }
}
