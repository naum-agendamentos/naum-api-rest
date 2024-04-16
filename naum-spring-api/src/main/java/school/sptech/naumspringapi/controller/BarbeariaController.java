package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaCriacaoDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.mapper.BarbeariaMapper;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.repository.BarbeariaRepository;

import java.util.List;

@RestController
@RequestMapping("/barbearias")
public class BarbeariaController {
    @Autowired
    private BarbeariaRepository barbeariaRepository;

    @PostMapping
    public ResponseEntity<BarbeariaListagemDto> cadastrar(@RequestBody @Valid BarbeariaCriacaoDto novaBarbearia) {
        Barbearia barbearia = BarbeariaMapper.toEntity(novaBarbearia);

        Barbearia barbeariaSalva = barbeariaRepository.save(barbearia);

        BarbeariaListagemDto barbeariaListagemDto = BarbeariaMapper.toDto(barbeariaSalva);

        return ResponseEntity.status(201).body(barbeariaListagemDto);
    }

    @GetMapping
    public ResponseEntity<List<BarbeariaListagemDto>> listarBarbearias() {
        List<Barbearia> lista = barbeariaRepository.findAll();

        if (lista.isEmpty()) return ResponseEntity.status(204).build();

        List<BarbeariaListagemDto> listaAuxiliar = BarbeariaMapper.toDto(lista);
        return ResponseEntity.status(200).body(listaAuxiliar);
    }
}
