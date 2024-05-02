package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoCriacaoDto;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoListagemDto;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoAtualizacaoDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @ApiOperation("Criar uma avaliação nova.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Barbearia criada com sucesso!"),
            @ApiResponse(code = 400, message = "Dados inválidos.")
    })
    @Operation(summary = "Criar avaliação", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/{idBarbearia}")
    public ResponseEntity<AvaliacaoListagemDto> realizarAvaliacao(@RequestBody @Valid AvaliacaoCriacaoDto novaAvaliacao, Long idAvaliacao, @PathVariable Long idBarbearia){
        return ResponseEntity.ok(avaliacaoService.criarAvaliacao(novaAvaliacao, idAvaliacao, idBarbearia));
    }

    @ApiOperation("Buscar avaliação por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Avaliação encontrada com sucesso!"),
            @ApiResponse(code = 404, message = "Avaliação não encontrada.")
    })
    @Operation(summary = "Listar avaliações", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{idBarbearia}")
    public ResponseEntity<List<AvaliacaoListagemDto>> listarAvaliacoes(@RequestParam("idCliente") Long idCliente, @PathVariable Long idBarbearia, @RequestBody Integer estrela) {
        List<AvaliacaoListagemDto> avaliacaoResponse = avaliacaoService.listarAvaliacaoDinamica(estrela, idBarbearia, idCliente);
        if (avaliacaoResponse == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(avaliacaoResponse);
    }

    @ApiOperation("Atualizar uma avaliação por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Avaliação atualizada com sucesso!"),
            @ApiResponse(code = 400, message = "Dados inválidos."),
            @ApiResponse(code = 404, message = "Avaliação não encontrada.")
    })
    @Operation(summary = "Listar avaliações", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{idAvaliacao}")
    public ResponseEntity<AvaliacaoListagemDto> atualizarAvaliacao(@PathVariable Long idAvaliacao, @RequestBody @Valid AvaliacaoAtualizacaoDto avaliacaoAtualizacaoDto) {
        AvaliacaoListagemDto avaliacaoAtualizada = avaliacaoService.atualizarAvaliacao(idAvaliacao, avaliacaoAtualizacaoDto);
        return ResponseEntity.status(HttpStatus.OK).body(avaliacaoAtualizada);
    }

    @ApiOperation("Deletar uma avaliação.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Avaliação Deletada com sucesso!"),
            @ApiResponse(code = 404, message = "Avaliação não encontrada.")
    })
    @Operation(summary = "Listar avaliações", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/deletarAvaliacao/{id}")
    public ResponseEntity<Void> deletarAvaliacao(@PathVariable Long id) {
        avaliacaoService.deletarAvaliacao(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

