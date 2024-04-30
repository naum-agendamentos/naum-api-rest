package school.sptech.naumspringapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.dto.servicoDto.ServicoCriacaoDto;
import school.sptech.naumspringapi.dto.servicoDto.ServicoListagemDto;
import school.sptech.naumspringapi.entity.Servico;
import school.sptech.naumspringapi.repository.ServicoRepository;
import school.sptech.naumspringapi.service.ServicoService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    @Operation(summary = "Criar serviço", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<ServicoListagemDto> criarServico(@RequestBody @Valid ServicoCriacaoDto novoServico, @RequestParam("idBarbearia") Long idBarbearia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoService.criarServicoPorBarbearia(novoServico, idBarbearia));
    }

    @Operation(summary = "Listar serviços", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<ServicoListagemDto>> listarServicos(@RequestParam("idBarbearia") Long idBarbearia) {
        List<ServicoListagemDto> servicoResponseDto = servicoService.listarServicosPorBarbearia(idBarbearia);
        if (servicoResponseDto.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.OK).body(servicoResponseDto);
    }

    @Operation(summary = "Buscar serviço por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{id}")
    public ResponseEntity<ServicoListagemDto> listarServicoPorId(@PathVariable Long idServico, @RequestParam("idBarbearia") Long idBarbearia) {
        return ResponseEntity.ok(servicoService.buscarServicoPorId(idBarbearia, idServico));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Servico> atualizarServico(@PathVariable Integer id, @RequestBody Servico servicoAtualizado) {
//
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> excluirServico(@PathVariable Long id) {
//
//    }
}
