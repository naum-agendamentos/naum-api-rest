package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @Operation(summary = "Criar avaliação", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/{idBarbearia}")
    public ResponseEntity<AvaliacaoListagemDto> realizarAvaliacao(@RequestBody @Valid AvaliacaoCriacaoDto novaAvaliacao, Long idAvaliacao, @PathVariable Long idBarbearia){
        return ResponseEntity.ok(avaliacaoService.criarAvaliacao(novaAvaliacao, idAvaliacao, idBarbearia));
    }

    @Operation(summary = "Listar avaliações", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/idBarbearia")
    public ResponseEntity<List<AvaliacaoListagemDto>> listarAvaliacoes(@RequestParam("idCliente") Long idCliente, @PathVariable Long idBarbearia, @RequestBody Integer estrela) {
        List<AvaliacaoListagemDto> avaliacaoResponse = avaliacaoService.listarAvaliacaoDinamica(estrela, idBarbearia, idCliente);
        if (avaliacaoResponse == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(avaliacaoResponse);
    }

    @PutMapping("/{idAvaliacao}")
    public ResponseEntity<AvaliacaoListagemDto> atualizarAvaliacao(@PathVariable Long idAvaliacao, @RequestBody @Valid AvaliacaoAtualizacaoDto avaliacaoAtualizacaoDto) {
        AvaliacaoListagemDto avaliacaoAtualizada = avaliacaoService.atualizarAvaliacao(idAvaliacao, avaliacaoAtualizacaoDto);
        return ResponseEntity.status(HttpStatus.OK).body(avaliacaoAtualizada);
    }

    @DeleteMapping("/deletarAvaliacao/{id}")
    public ResponseEntity<Void> deletarAvaliacao(@PathVariable Long id) {
        avaliacaoService.deletarAvaliacao(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

