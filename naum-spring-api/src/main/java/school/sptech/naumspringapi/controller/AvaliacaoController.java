//package school.sptech.naumspringapi.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import school.sptech.naumspringapi.entity.Avaliacao;
//import school.sptech.naumspringapi.repository.AvaliacaoRepository;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/avaliacoes")
//public class AvaliacaoController {
//
//    @Autowired
//    private AvaliacaoRepository avaliacaoRepository;
//
//    @PostMapping("/realizarAvaliacao")
//    public ResponseEntity<Avaliacao> realizarAvaliacao(@RequestBody Avaliacao novaAvaliacao) {
//        Avaliacao avaliacaoSalva = avaliacaoRepository.save(novaAvaliacao);
//        return ResponseEntity.ok(avaliacaoSalva);
//    }
//
//    @GetMapping("/listarAvaliacao")
//    public ResponseEntity<List<Avaliacao>> listarAvaliacao() {
//
//    }
//
//    @PutMapping("/atualizarAvaliacao/{id}")
//    public ResponseEntity<Avaliacao> atualizarAvaliacao(@PathVariable int id, @RequestBody Avaliacao avaliacaoAtualizada) {
//
//    }
//
//    @DeleteMapping("/deletarAvaliacao/{id}")
//    public ResponseEntity<Void> deletarAvaliacao(@PathVariable int id) {
//
//    }
//}
//
