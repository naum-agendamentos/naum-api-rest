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
    private final EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<BarbeariaListagemDto> cadastrar(@RequestBody @Valid BarbeariaCriacaoDto novaBarbearia) {
        enderecoService.cadastrarEndereco(novaBarbearia);
        BarbeariaListagemDto barbeariaCriada = barbeariaService.criarBarbearia(novaBarbearia);
        return ResponseEntity.ok(barbeariaCriada);
    }

    @GetMapping
    public ResponseEntity<List<BarbeariaListagemDto>> listarBarbearias() {
        List<BarbeariaListagemDto> barbeariaListagem = barbeariaService.listarBarbearia();
        if (barbeariaListagem == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(barbeariaListagem);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<BarbeariaListagemDto> atualizarBarbearia(@PathVariable int id, @RequestBody @Valid BarbeariaCriacaoDto novaBarbearia) {

        BarbeariaListagemDto barbeariaAtualizada = barbeariaService.atualizarBarbearia(novaBarbearia, id);

        if (barbeariaAtualizada == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(barbeariaAtualizada);
    }
}

