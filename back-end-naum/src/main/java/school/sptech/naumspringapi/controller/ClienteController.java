package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroCriacaoDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroDesativacaoDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;
import school.sptech.naumspringapi.dto.clienteDto.ClienteCriacaoDto;
import school.sptech.naumspringapi.dto.clienteDto.ClienteListagemDto;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.mapper.BarbeiroMapper;
import school.sptech.naumspringapi.mapper.ClienteMapper;
import school.sptech.naumspringapi.repository.ClienteRepository;
import school.sptech.naumspringapi.service.BarbeiroService;
import school.sptech.naumspringapi.service.ClienteService;
import school.sptech.naumspringapi.service.usuario.UsuarioService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private final ClienteService clienteService;

    @Autowired
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<ClienteListagemDto> cadastrar(@RequestBody @Valid ClienteCriacaoDto novoCliente){
        Cliente clienteCriado = clienteService.criar(novoCliente);

        ClienteListagemDto clienteListagemDto = ClienteMapper.toDto(clienteCriado);
        return ResponseEntity.ok(clienteListagemDto);
    }

    @GetMapping
    public ResponseEntity<List<ClienteListagemDto>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        if (clientes == null) return ResponseEntity.noContent().build();
        List<ClienteListagemDto> clienteListagemDtos = ClienteMapper.toDto(clientes);
        return ResponseEntity.ok(clienteListagemDtos);
    }

//    @GetMapping("/local")
//    public ResponseEntity<List<BarbeiroListagemDto>> listarDaMinhaBarbearia() {
//        List<Barbeiro> barbeiros = barbeiroService.listaBarbeirosPorBarbearia();
//
//        List<BarbeiroListagemDto> dto = BarbeiroMapper.toDto(barbeiros);
//
//        if (dto == null) return ResponseEntity.noContent().build();
//        return ResponseEntity.ok(dto);
//    }


    @PutMapping("/{id}")
    public ResponseEntity<ClienteListagemDto> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteCriacaoDto novoCliente) {
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, novoCliente);

        if (clienteAtualizado == null) return ResponseEntity.notFound().build();

        ClienteListagemDto clienteListagemDto = ClienteMapper.toDto(clienteAtualizado);

        return ResponseEntity.ok(clienteListagemDto);
    }

}
