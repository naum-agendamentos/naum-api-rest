package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.dto.servicoDto.ServicoCriacaoDto;
import school.sptech.naumspringapi.dto.servicoDto.ServicoListagemDto;
import school.sptech.naumspringapi.entity.Servico;
import school.sptech.naumspringapi.service.ServicoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    @PostMapping
    public ResponseEntity<ServicoListagemDto> criarServico(@RequestBody @Valid ServicoCriacaoDto novoServico, @RequestParam("idBarbearia") Integer idBarbearia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoService.criarServicoPorBarbearia(novoServico, idBarbearia));
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
