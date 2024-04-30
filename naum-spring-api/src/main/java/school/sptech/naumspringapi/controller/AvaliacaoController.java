package school.sptech.naumspringapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoCriacaoDto;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoCriacaoDto;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoListagemDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroCriacaoDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;
import school.sptech.naumspringapi.entity.Avaliacao;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.entity.Servico;
import school.sptech.naumspringapi.mapper.AvaliacaoMapper;
import school.sptech.naumspringapi.mapper.BarbeiroMapper;
import school.sptech.naumspringapi.repository.AvaliacaoRepository;
import school.sptech.naumspringapi.service.AvaliacaoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;


//    @PostMapping
//    public ResponseEntity<AvaliacaoListagemDto> realizarAvaliacao(@RequestBody @Valid AvaliacaoCriacaoDto novaAvaliacao, Long idAvaliacao){
//        return ResponseEntity.ok(avaliacaoService.criarAvaliacao(novaAvaliacao, idAvaliacao));
//    }

    @Operation(summary = "Listar avaliações", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<AvaliacaoListagemDto>> listarAvaliacoes(@RequestParam("idCliente") Long idCliente, @RequestParam("idBarbearia") Long idBarbearia, @RequestBody Integer estrela) {
        List<AvaliacaoListagemDto> avaliacaoResponse = avaliacaoService.listarAvaliacaoDinamica(estrela, idBarbearia, idCliente);
        if (avaliacaoResponse == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(avaliacaoResponse);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<AvaliacaoListagemDto> atualizarAvaliacao(@PathVariable Long idBarbeiro, @RequestBody @Valid BarbeiroCriacaoDto novoBarbeiro) {
//        Avaliacao avaliacaoAtualizada = avaliacaoService.atualizarAvaliacao();
//
//        if (avaliacaoAtualizada == null) return ResponseEntity.notFound().build();
//
//        AvaliacaoListagemDto avaliacaoListagemDto = AvaliacaoMapper.toDto(avaliacaoAtualizada);
//
//        return ResponseEntity.ok(avaliacaoListagemDto);
//    }
//
//    @DeleteMapping("/deletarAvaliacao/{id}")
//    public ResponseEntity<Void> deletarAvaliacao(@PathVariable int id) {
//        Avaliacao deletarAvaliacao = avaliacaoService.deletarAvaliacao(id, novoBarbeiro);
//        if (avaliacaoOptional.isPresent()) {
//            avaliacaoService.deleteById(id);
//            return ResponseEntity.status(200).build();
//        } else {
//            return ResponseEntity.status(404).build();
//        }
//    }
}

