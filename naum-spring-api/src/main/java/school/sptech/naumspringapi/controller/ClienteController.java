package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
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

    @ApiOperation("Cadastrar um novo cliente.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente cadastrado com sucesso!"),
            @ApiResponse(code = 400, message = "Dados inválidos.")
    })
    @Operation(summary = "Cadastrar clientes", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<ClienteListagemDto> cadastrar(@RequestBody @Valid ClienteCriacaoDto novoCliente) {
        return ResponseEntity.ok(clienteService.criar(novoCliente));
    }

    @ApiOperation("Listar clientes.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes listados com sucesso!"),
            @ApiResponse(code = 204, message = "Não existem clientes cadastrados.")
    })
    @Operation(summary = "Listar clientes", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<ClienteListagemDto>> listarClientes() {
        List<ClienteListagemDto> clientes = clienteService.listarClientes();
        if (clientes.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(clientes);
    }

    @ApiOperation("Buscar cliente por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente encontrado com sucesso!"),
            @ApiResponse(code = 404, message = "Cliente não encontrado.")
    })
    @Operation(summary = "Buscar cliente por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteListagemDto> buscarClientePorId(@PathVariable Long idCliente) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarClientePorIdDto(idCliente));
    }

    @ApiOperation("Atualizar um novo cliente.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente atualizado com sucesso!"),
            @ApiResponse(code = 400, message = "Dados inválidos."),
            @ApiResponse(code = 400, message = "Cliente não encontrado.")
    })
    @Operation(summary = "Atualizar cliente por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}")
    public ResponseEntity<ClienteListagemDto> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteCriacaoDto novoCliente) {
        return ResponseEntity.ok(clienteService.atualizarCliente(id, novoCliente));
    }

    @ApiOperation("Deletar cliente por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente deletado com sucesso!"),
            @ApiResponse(code = 404, message = "Cliente não encontrado"),
    })
    @Operation(summary = "Deletar cliente por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long idCliente) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
