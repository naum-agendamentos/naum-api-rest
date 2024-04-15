package school.sptech.naumspringapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServiceRepository serviceRepository;

    @PostMapping
    public ResponseEntity<Servico> criarServico(@RequestBody @Valid Servico novoServico) {
        Servico servicoSalvo = serviceRepository.save(novoServico);
        return ResponseEntity.status(201).body(servicoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Servico>> obterTodosServicos() {
        List<Servico> lista = serviceRepository.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> obterServicoPorId(@PathVariable Integer id) {
        Optional<Servico> servicoOpcional = serviceRepository.findById(id);
        if (servicoOpcional.isPresent()) {
            Servico servicoEncontrado = servicoOpcional.get();
            return ResponseEntity.status(200).body(servicoEncontrado);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizarServico(@PathVariable Integer id, @RequestBody Servico servicoAtualizado) {
        Optional<Servico> servicoOpcional = serviceRepository.findById(id);
        if (servicoOpcional.isPresent()) {
            Servico servicoEncontrado = servicoOpcional.get();
            servicoEncontrado.setNomeServico(servicoAtualizado.getNomeServico());
            servicoEncontrado.setPreco(servicoAtualizado.getPreco());

            Servico servicoAtualizadoSalvo = serviceRepository.save(servicoEncontrado);
            return ResponseEntity.status(200).body(servicoAtualizadoSalvo);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirServico(@PathVariable Integer id) {
        Optional<Servico> servicoOpcional = serviceRepository.findById(id);
        if (servicoOpcional.isPresent()) {
            serviceRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
