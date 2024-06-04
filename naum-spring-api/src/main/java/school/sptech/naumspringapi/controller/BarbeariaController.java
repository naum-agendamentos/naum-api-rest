package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.mapper.BarbeariaMapper;
import school.sptech.naumspringapi.service.BarbeariaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaCriacaoDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Api(tags = "BarbeariaController", description = "Controller da barbearia.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/barbearias")
public class BarbeariaController {

    private final BarbeariaService barbeariaService;

    @ApiOperation("Criar uma barbearia nova.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Barbearia criada com sucesso!"),
            @ApiResponse(code = 422, message = "Dados inválidos."),
    })
    @Operation(summary = "Cadastrar barbearia", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<BarbeariaListagemDto> cadastrar(@RequestBody @Valid BarbeariaCriacaoDto novaBarbearia) {
        Barbearia barbeariaCriada = barbeariaService.criarBarbearia(novaBarbearia);
        URI uri = URI.create("/barbearias/" + barbeariaCriada.getId());
        return ResponseEntity.created(uri).body(BarbeariaMapper.toDto(barbeariaCriada));
    }

    @ApiOperation("Lista barbearia por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Barbearia listada com sucesso!"),
            @ApiResponse(code = 422, message = "Bados inválidos"),
            @ApiResponse(code = 404, message = "Barbearia não encontrada.")
    })
    @Operation(summary = "Listar barbearia por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{idBarbearia}")
    public ResponseEntity<BarbeariaListagemDto> buscarBarbeariaPorId(@PathVariable Long idBarbearia) {
        return ResponseEntity.ok(BarbeariaMapper.toDto(barbeariaService.buscarPorId(idBarbearia)));
    }

    @ApiOperation("Lista todas as barbearias cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Barbearias listadas com sucesso!"),
            @ApiResponse(code = 204, message = "Não existem barbearias cadastradas."),
    })
    @Operation(summary = "Listar barbearias", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<BarbeariaListagemDto>> listarBarbearias() {
        List<Barbearia> barbeariaListagem = barbeariaService.listarBarbearia();
        if (Objects.isNull(barbeariaListagem)) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(BarbeariaMapper.toDto(barbeariaListagem));
    }

    @ApiOperation("Atualizar uma barbearia pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Barbearia atualizada com sucesso!"),
            @ApiResponse(code = 400, message = "Dados inválidos."),
            @ApiResponse(code = 404, message = "Barbearia não encontrada."),
    })
    @Operation(summary = "Atualizar uma barbearia pelo ID", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}")
    public ResponseEntity<BarbeariaListagemDto> atualizarBarbearia(@PathVariable Long id, @RequestBody @Valid BarbeariaCriacaoDto novaBarbearia) {
        Barbearia barbeariaAtualizada = barbeariaService.atualizarBarbearia(id, novaBarbearia);
        return ResponseEntity.ok(BarbeariaMapper.toDto(barbeariaAtualizada));
    }

    @ApiOperation("Desativar uma barbearia por ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Barbearia desativada com sucesso!"),
            @ApiResponse(code = 400, message = "Dados inválidos."),
            @ApiResponse(code = 404, message = "Barbearia não encontrada."),
    })
    @Operation(summary = "Desativar uma barbearia pelo ID", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/desativar/{id}")
    public ResponseEntity<BarbeariaListagemDto> desativarBarbearia(@PathVariable Long id){
        return ResponseEntity.status(200).body(BarbeariaMapper.toDto(barbeariaService.desativarBarbearia(id)));
    }
}

