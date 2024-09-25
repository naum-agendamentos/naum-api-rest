package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.dto.muralAvisosDto.MuralAvisosCriacaoDto;
import school.sptech.naumspringapi.dto.muralAvisosDto.MuralAvisosAtualizacaoDto;
import school.sptech.naumspringapi.dto.muralAvisosDto.MuralAvisosListagemDto;
import school.sptech.naumspringapi.mapper.MuralAvisosMapper;
import school.sptech.naumspringapi.service.MuralAvisosService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.net.URI;
import java.util.List;

@Api(tags = "MuralAvisosController", description = "Controller para o gerenciamento de avisos no mural")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mural-avisos")
public class MuralAvisosController {

    private final MuralAvisosService muralAvisosService;

    @ApiOperation("Cadastrar um novo aviso no mural.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Aviso cadastrado com sucesso!"),
            @ApiResponse(code = 422, message = "Dados inválidos.")
    })
    @Operation(summary = "Cadastrar um novo aviso", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<MuralAvisosListagemDto> cadastrarAviso(@RequestBody @Valid MuralAvisosCriacaoDto dto) {
        var muralAvisos = muralAvisosService.criarAviso(dto);
        URI uri = URI.create("/mural-avisos/" + muralAvisos.getId());
        return ResponseEntity.created(uri).body(MuralAvisosMapper.toDto(muralAvisos));
    }

    @ApiOperation("Buscar um aviso por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Aviso encontrado com sucesso!"),
            @ApiResponse(code = 404, message = "Aviso não encontrado.")
    })
    @Operation(summary = "Buscar um aviso por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{idAviso}")
    public ResponseEntity<MuralAvisosListagemDto> buscarAvisoPorId(@PathVariable Long idAviso) {
        var aviso = muralAvisosService.buscarPorId(idAviso);
        return ResponseEntity.ok(MuralAvisosMapper.toDto(aviso));
    }

    @ApiOperation("Listar todos os avisos do mural.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Avisos listados com sucesso!"),
            @ApiResponse(code = 204, message = "Nenhum aviso encontrado.")
    })
    @Operation(summary = "Listar todos os avisos", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<MuralAvisosListagemDto>> listarTodosAvisos() {
        List<MuralAvisosListagemDto> avisos = MuralAvisosMapper.toDto(muralAvisosService.listarTodos());
        if (avisos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(avisos);
    }

    @ApiOperation("Atualizar um aviso existente.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Aviso atualizado com sucesso!"),
            @ApiResponse(code = 404, message = "Aviso não encontrado."),
            @ApiResponse(code = 400, message = "Dados inválidos.")
    })
    @Operation(summary = "Atualizar um aviso", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{idAviso}")
    public ResponseEntity<MuralAvisosListagemDto> atualizarAviso(@PathVariable Long idAviso, @RequestBody @Valid MuralAvisosAtualizacaoDto dto) {
        var avisoAtualizado = muralAvisosService.atualizarAviso(idAviso, dto);
        return ResponseEntity.ok(MuralAvisosMapper.toDto(avisoAtualizado));
    }

    @ApiOperation("Deletar um aviso por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Aviso deletado com sucesso!"),
            @ApiResponse(code = 404, message = "Aviso não encontrado.")
    })
    @Operation(summary = "Deletar um aviso", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{idAviso}")
    public ResponseEntity<Void> deletarAviso(@PathVariable Long idAviso) {
        muralAvisosService.deletarAviso(idAviso);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Listar avisos por barbeiro.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Avisos listados com sucesso!"),
            @ApiResponse(code = 204, message = "Nenhum aviso encontrado para o barbeiro."),
            @ApiResponse(code = 404, message = "Barbeiro não encontrado.")
    })
    @Operation(summary = "Listar avisos de um barbeiro", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/barbeiro/{idBarbeiro}")
    public ResponseEntity<List<MuralAvisosListagemDto>> listarAvisosPorBarbeiro(@PathVariable Long idBarbeiro) {
        List<MuralAvisosListagemDto> avisos = MuralAvisosMapper.toDto(muralAvisosService.listarPorBarbeiro(idBarbeiro));
        if (avisos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(avisos);
    }
}
