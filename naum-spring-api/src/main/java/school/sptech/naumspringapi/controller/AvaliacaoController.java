package school.sptech.naumspringapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.repository.AvaliacaoRepository;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @PostMapping("/realizarAvaliacao")
    public ResponseEntity<Avaliacao> realizarAvaliacao(@RequestBody Avaliacao novaAvaliacao) {
        Avaliacao avaliacaoSalva = avaliacaoRepository.save(novaAvaliacao);
        return ResponseEntity.ok(avaliacaoSalva);
    }

    @GetMapping("/listarAvaliacao")
    public List<Avaliacao> listarAvaliacao() {
        return avaliacaoRepository.findAll();
    }

    @PutMapping("/atualizarAvaliacao/{id}")
    public ResponseEntity<Avaliacao> atualizarAvaliacao(@PathVariable int id, @RequestBody Avaliacao avaliacaoAtualizada) {
        Avaliacao avaliacaoExistente = avaliacaoRepository.findById(id);
        if (avaliacaoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        avaliacaoExistente.setNota(avaliacaoAtualizada.getNota());
        avaliacaoExistente.setComentario(avaliacaoAtualizada.getComentario());
        Avaliacao avaliacaoAtualizadaSalva = avaliacaoRepository.save(avaliacaoExistente);

        return ResponseEntity.ok(avaliacaoAtualizadaSalva);
    }

    @DeleteMapping("/deletarAvaliacao/{id}")
    public ResponseEntity<Void> deletarAvaliacao(@PathVariable int id) {
        Avaliacao avaliacaoExistente = avaliacaoRepository.findById(id);
        if (avaliacaoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        avaliacaoRepository.delete(avaliacaoExistente);
        return ResponseEntity.ok().build();
    }
}

