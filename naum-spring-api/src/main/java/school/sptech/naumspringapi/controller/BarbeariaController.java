package school.sptech.naumspringapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.service.BarbeariaService;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaCriacaoDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaAtualizacaoDto;

import java.util.List;

@Api(tags = "BarbeariaController", description = "Controller da barbearia.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/barbearias")
public class BarbeariaController {

    private final BarbeariaService barbeariaService;

    @ApiOperation("Criar uma barbearia nova.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Barbearia criada com sucesso!"),
            @ApiResponse(code = 400, message = "Dados inválidos."),
    })
    @Operation(summary = "Cadastrar barbearia", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<BarbeariaListagemDto> cadastrar(@RequestBody @Valid BarbeariaCriacaoDto novaBarbearia) {
        BarbeariaListagemDto barbeariaCriada = barbeariaService.criarBarbearia(novaBarbearia);
        return ResponseEntity.ok(barbeariaCriada);
    }

    @ApiOperation("Lista todas as barbearias cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Barbearias listadas com sucesso!"),
            @ApiResponse(code = 404, message = "Não existem barbearias cadastradas."),
    })
    @Operation(summary = "Listar barbearias", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<BarbeariaListagemDto>> listarBarbearias() {
        List<BarbeariaListagemDto> barbeariaListagem = barbeariaService.listarBarbearia();
        if (barbeariaListagem == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(barbeariaListagem);
    }

    @ApiOperation("Atualizar uma barbearia pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Barbearia atualizada com sucesso!"),
            @ApiResponse(code = 400, message = "Dados inválidos."),
            @ApiResponse(code = 404, message = "Barbearia não encontrada."),
    })
    @Operation(summary = "Atualizar uma barbearia pelo ID.", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}")
    public ResponseEntity<BarbeariaListagemDto> atualizarBarbearia(@PathVariable Long id, @RequestBody @Valid BarbeariaCriacaoDto novaBarbearia) {
        BarbeariaListagemDto barbeariaAtualizada = barbeariaService.atualizarBarbearia(id, novaBarbearia);

        if (barbeariaAtualizada == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(barbeariaAtualizada);
    }

    @ApiOperation("Desativar uma barbearia por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Barbearia desativada com sucesso!"),
            @ApiResponse(code = 400, message = "Dados inválidos."),
            @ApiResponse(code = 404, message = "Barbearia não encontrada."),
    })
    @Operation(summary = "Desativar uma barbearia pelo ID.", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/desativar/{id}")
    public ResponseEntity<BarbeariaAtualizacaoDto> desativarBarbearia(@PathVariable Long id){
        BarbeariaAtualizacaoDto barbeariaDesativada = barbeariaService.desativarBarbearia(id);

        if(barbeariaDesativada == null){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(barbeariaDesativada);
    }
}

