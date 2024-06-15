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
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoListagemDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;
import school.sptech.naumspringapi.entity.Agendamento;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.entity.Servico;
import school.sptech.naumspringapi.mapper.AgendamentoMapper;
import school.sptech.naumspringapi.mapper.BarbeiroMapper;
import school.sptech.naumspringapi.mapper.ClienteMapper;
import school.sptech.naumspringapi.service.AgendamentoService;
import school.sptech.naumspringapi.service.BarbeiroService;
import school.sptech.naumspringapi.service.ClienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import school.sptech.naumspringapi.dto.clienteDto.ClienteCriacaoDto;
import school.sptech.naumspringapi.dto.clienteDto.ClienteListagemDto;
import school.sptech.naumspringapi.service.ServicoService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final BarbeiroService barbeiroService;
    private final AgendamentoService agendamentoService;
    private final ServicoService servicoService;

    @ApiOperation("Cadastrar um novo cliente.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente cadastrado com sucesso!"),
            @ApiResponse(code = 400, message = "Dados inválidos.")
    })
    @Operation(summary = "Cadastrar clientes", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<ClienteListagemDto> cadastrar(@RequestBody @Valid ClienteCriacaoDto novoCliente) {
        Cliente cliente = clienteService.criar(novoCliente);
        URI uri = URI.create("/clientes/" + cliente.getId());
        return ResponseEntity.created(uri).body(ClienteMapper.toDto(cliente));
    }

    @ApiOperation("Listar clientes.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes listados com sucesso!"),
            @ApiResponse(code = 204, message = "Não existem clientes cadastrados.")
    })
    @Operation(summary = "Listar clientes", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<ClienteListagemDto>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        if (clientes.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(ClienteMapper.toDto(clientes));
    }

    @ApiOperation("Buscar cliente por UserId.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente encontrado com sucesso!"),
            @ApiResponse(code = 404, message = "Cliente não encontrado.")
    })
    @Operation(summary = "Buscar cliente por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/usuario")
    public ResponseEntity<ClienteListagemDto> buscarClientePorId(@RequestParam("idUsuario") Long idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(ClienteMapper.toDto(clienteService.buscarPorUsuario(idUsuario)));
    }

    @ApiOperation("Atualizar um novo cliente.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente atualizado com sucesso!"),
            @ApiResponse(code = 422, message = "Dados inválidos."),
            @ApiResponse(code = 404, message = "Cliente não encontrado.")
    })
    @Operation(summary = "Atualizar cliente por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}")
    public ResponseEntity<ClienteListagemDto> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteCriacaoDto novoCliente) {
        return ResponseEntity.ok(ClienteMapper.toDto(clienteService.atualizarCliente(id, novoCliente)));
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

    @ApiOperation("Buscar agendamentos por cliente.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agendamentos listados com sucesso!"),
            @ApiResponse(code = 204, message = "Sem agendamentos para retornar"),
    })
    @Operation(summary = "Buscar agendamentos por cliente", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{clienteId}/agendamentos")
    public ResponseEntity<List<AgendamentoListagemDto>> listarAgendamentosPorCliente(@PathVariable Long clienteId) {
        List<Agendamento> agendamentos = agendamentoService.listarPorCliente(clienteId);
        if (agendamentos.isEmpty()) return ResponseEntity.noContent().build();
        List<List<Servico>> listasDeServicos = servicoService.buscarServicosPorAgendamentos(agendamentos);
        List<AgendamentoListagemDto> agendamentoListagemDtos = new ArrayList<>();
        for (int i = 0; i < agendamentos.size(); i++) {
            agendamentoListagemDtos.add(AgendamentoMapper.toDto(agendamentos.get(i), listasDeServicos.get(i)));
        }
        return ResponseEntity.ok(agendamentoListagemDtos);
    }

    @ApiOperation("Buscar barbeiros para clientes.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Barbeiros buscados com sucesso!"),
            @ApiResponse(code = 204, message = "Sem barbeiros para retornar"),
    })
    @Operation(summary = "Buscar barbeiros por barbearia para clientes", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/berbeiros")
    public ResponseEntity<List<BarbeiroListagemDto>> listarBarbeirosParaCliente() {
        return ResponseEntity.ok(BarbeiroMapper.toDto(barbeiroService.listaBarbeirosPorBarbearia()));
    }

}
