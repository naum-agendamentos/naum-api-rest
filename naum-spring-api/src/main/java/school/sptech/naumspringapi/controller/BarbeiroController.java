package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.entity.Agendamento;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.mapper.BarbeiroMapper;
import school.sptech.naumspringapi.service.AgendamentoService;
import school.sptech.naumspringapi.service.BarbeiroService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroCriacaoDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroDesativacaoDto;

import java.net.URI;
import java.util.List;

@Api(tags = "BarbeiroController", description = "Controller do barbeiro")
@RequiredArgsConstructor
@RestController
@RequestMapping("/barbeiros")
public class BarbeiroController {

    private final BarbeiroService barbeiroService;

    private final AgendamentoService agendamentoService;

    @ApiOperation("Cadastrar um barbeiro novo.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Barbeiro cadastrado com sucesso!"),
            @ApiResponse(code = 422, message = "Dados inválidos.")
    })
    @Operation(summary = "Cadastrar um barbeiro", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<BarbeiroListagemDto> cadastrar(@RequestBody @Valid BarbeiroCriacaoDto novoBarbeiro){
        Barbeiro barbeiro = barbeiroService.criarBarbeiro(novoBarbeiro);
        URI uri = URI.create("/barbeiros/" + barbeiro.getId());
        return ResponseEntity.created(uri).body(BarbeiroMapper.toDto(barbeiro));
    }

    @ApiOperation("Listar barbeiros de uma barbearia.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Barbeiros listados com sucesso!"),
            @ApiResponse(code = 204, message = "Não existem barbeiros cadastrados.")
    })
    @Operation(summary = "Listar barbeiros de uma barbearia", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<BarbeiroListagemDto>> listarDaMinhaBarbearia() {
        List<Barbeiro> barbeiros = barbeiroService.listaBarbeirosPorBarbearia();
        if (barbeiros.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.OK).body(BarbeiroMapper.toDto(barbeiros));
    }

    @ApiOperation("Buscar barbeiro por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Barbeiro econtrado com sucesso!"),
            @ApiResponse(code = 404, message = "Barbeiro não encontrado.")
    })
    @Operation(summary = "Busca um barbeiro por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{idBarbeiro}")
    public ResponseEntity<BarbeiroListagemDto> buscarBarbeiroPorId(@PathVariable Long idBarbeiro) {
        return ResponseEntity.status(HttpStatus.OK).body(BarbeiroMapper.toDto(barbeiroService.buscarPorId(idBarbeiro)));
    }

    @ApiOperation("Atualizar um barbeiro por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Barbeiro atualizado com sucesso!"),
            @ApiResponse(code = 400, message = "Dados inválidos"),
            @ApiResponse(code = 404, message = "Barbeiro não encontrado.")
    })
    @Operation(summary = "Atualizar um barbeiro", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}")
    public ResponseEntity<BarbeiroListagemDto> atualizarBarbeiro(@PathVariable Long id, @RequestBody @Valid BarbeiroCriacaoDto novoBarbeiro) {
        return ResponseEntity.ok(BarbeiroMapper.toDto(barbeiroService.atualizarBarbeiro(id, novoBarbeiro)));
    }

    @ApiOperation("Desativar barbeiro por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Barbeiro desativado com sucesso!"),
            @ApiResponse(code = 404, message = "Barbeiro não encontrado.")
    })
    @Operation(summary = "Desativar um barbeiro", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/desativar/{id}")
    public ResponseEntity<BarbeiroDesativacaoDto> desativarBarbeiro(@PathVariable Long id){
        return ResponseEntity.status(200).body(BarbeiroMapper.toDtoDesativacao(barbeiroService.desativarBarbeiro(id)));
    }

    @GetMapping("/{barbeiroId}/agendamentos")
    public List<Agendamento> listarAgendamentosPorBarbeiro(@PathVariable Long barbeiroId) {
        return agendamentoService.listarPorBarbeiro(barbeiroId);
    }
}
