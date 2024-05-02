package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.service.ServicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import school.sptech.naumspringapi.dto.servicoDto.ServicoCriacaoDto;
import school.sptech.naumspringapi.dto.servicoDto.ServicoListagemDto;
import school.sptech.naumspringapi.dto.servicoDto.ServicoAtualizacaoDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    @ApiOperation("Cadastrar um novo serviço.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Serviço cadastrado com sucesso!"),
            @ApiResponse(code = 400, message = "Dados inválidos.")
    })
    @Operation(summary = "Criar serviço", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<ServicoListagemDto> criarServico(@RequestBody @Valid ServicoCriacaoDto novoServico, @RequestParam("idBarbearia") Long idBarbearia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoService.criarServicoPorBarbearia(novoServico, idBarbearia));
    }

    @ApiOperation("Listar serviços por barbearia.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Serviços listados com sucesso!"),
            @ApiResponse(code = 204, message = "Esta barbearia não possui serviços cadastrados.")
    })
    @Operation(summary = "Listar serviços", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<ServicoListagemDto>> listarServicos(@RequestParam("idBarbearia") Long idBarbearia) {
        List<ServicoListagemDto> servicoResponseDto = servicoService.listarServicosPorBarbearia(idBarbearia);
        if (servicoResponseDto.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.OK).body(servicoResponseDto);
    }

    @ApiOperation("Buscar serviço por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Serviço encontrado com sucesso!"),
            @ApiResponse(code = 404, message = "Serviço não encontrado.")
    })
    @Operation(summary = "Buscar serviço por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{idServico}")
    public ResponseEntity<ServicoListagemDto> listarServicoPorId(@PathVariable Long idServico, @RequestParam("idBarbearia") Long idBarbearia) {
        return ResponseEntity.ok(servicoService.buscarServicoPorId(idBarbearia, idServico));
    }

    @ApiOperation("Atualizar serviço por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Serviço atualizado com sucesso!"),
            @ApiResponse(code = 400, message = "Dados inválidos."),
            @ApiResponse(code = 404, message = "Serviço não encontrado.")
    })
    @Operation(summary = "Atualizar serviço por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{idServico}")
    public ResponseEntity<ServicoListagemDto> atualizarServico(@PathVariable Long idServico, @RequestBody @Valid ServicoAtualizacaoDto servicoAtualizado) {
        return ResponseEntity.status(HttpStatus.OK).body(servicoService.atualizarServicoPorId(idServico, servicoAtualizado));
    }

    @ApiOperation("Deletar serviço por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Serviço deletado com sucesso!"),
            @ApiResponse(code = 404, message = "Serviço não encontrado.")
    })
    @Operation(summary = "Deletar serviço por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{idServico}")
    public ResponseEntity<Void> excluirServico(@PathVariable Long idServico) {
        servicoService.excluirServico(idServico);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
