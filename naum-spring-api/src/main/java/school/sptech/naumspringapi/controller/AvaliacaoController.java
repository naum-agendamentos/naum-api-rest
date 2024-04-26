package school.sptech.naumspringapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoCriacaoDto;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoListagemDto;
import school.sptech.naumspringapi.entity.Avaliacao;
import school.sptech.naumspringapi.repository.AvaliacaoRepository;
import school.sptech.naumspringapi.service.AvaliacaoService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @PostMapping()
    public ResponseEntity<AvaliacaoListagemDto> realizarAvaliacao(@RequestBody AvaliacaoCriacaoDto avaliacaoDto, @RequestParam("clienteId, BarbeiroId") Integer clienteId, Integer barbeiroId) {
        AvaliacaoListagemDto avaliacaoResponse = avaliacaoService.criarAvaliacao(avaliacaoDto, clienteId, barbeiroId);
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoResponse);
    }

    @GetMapping("/listarAvaliacao")
    public ResponseEntity<List<AvaliacaoListagemDto>> listarAvaliacao() {

    }

    @PutMapping("/atualizarAvaliacao/{id}")
    public ResponseEntity<AvaliacaoListagemDto> atualizarAvaliacao(@PathVariable int id, @RequestBody Avaliacao avaliacaoAtualizada) {

    }

    @DeleteMapping("/deletarAvaliacao/{id}")
    public ResponseEntity<Void> deletarAvaliacao(@PathVariable int id) {

    }
}

