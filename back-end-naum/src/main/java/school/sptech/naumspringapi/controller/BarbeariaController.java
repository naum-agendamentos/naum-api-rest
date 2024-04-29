package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaAtualizacaoDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaCriacaoDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.dto.enderecoDto.EnderecoCriacaoDto;
import school.sptech.naumspringapi.dto.enderecoDto.EnderecoListagemDto;
import school.sptech.naumspringapi.entity.Endereco;
import school.sptech.naumspringapi.mapper.BarbeariaMapper;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.mapper.EnderecoMapper;
import school.sptech.naumspringapi.repository.BarbeariaRepository;
import school.sptech.naumspringapi.service.BarbeariaService;
import school.sptech.naumspringapi.service.EnderecoService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/barbearias")
public class BarbeariaController {

    private final BarbeariaService barbeariaService;

    @PostMapping
    public ResponseEntity<BarbeariaListagemDto> cadastrar(@RequestBody @Valid BarbeariaCriacaoDto novaBarbearia) {
        BarbeariaListagemDto barbeariaCriada = barbeariaService.criarBarbearia(novaBarbearia);
        return ResponseEntity.ok(barbeariaCriada);
    }

    @GetMapping
    public ResponseEntity<List<BarbeariaListagemDto>> listarBarbearias() {
        List<BarbeariaListagemDto> barbeariaListagem = barbeariaService.listarBarbearia();
        if (barbeariaListagem == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(barbeariaListagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarbeariaListagemDto> atualizarBarbearia(@PathVariable int id, @RequestBody @Valid BarbeariaCriacaoDto novaBarbearia) {
        BarbeariaListagemDto barbeariaAtualizada = barbeariaService.atualizarBarbearia(id, novaBarbearia);

        if (barbeariaAtualizada == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(barbeariaAtualizada);
    }

    @PutMapping("/desativar/{id}")
    public ResponseEntity<BarbeariaAtualizacaoDto> desativarBarbearia(@PathVariable int id){
        BarbeariaAtualizacaoDto barbeariaDesativada = barbeariaService.desativarBarbearia(id);

        if(barbeariaDesativada == null){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(barbeariaDesativada);
    }
}

