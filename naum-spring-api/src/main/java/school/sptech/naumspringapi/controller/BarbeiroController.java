package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.mapper.BarbeiroMapper;
import school.sptech.naumspringapi.service.BarbeiroService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import school.sptech.naumspringapi.service.usuario.UsuarioService;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroCriacaoDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroDesativacaoDto;
import school.sptech.naumspringapi.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.naumspringapi.service.usuario.autenticacao.dto.UsuarioTokenDto;

import java.util.List;

@Api(tags = "BarbeiroController", description = "")
@RequiredArgsConstructor
@RestController
@RequestMapping("/barbeiros")
public class BarbeiroController {

    private final BarbeiroService barbeiroService;
    private final UsuarioService usuarioService;

    @Operation(summary = "Cadastrar um barbeiro", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<BarbeiroListagemDto> cadastrar(@RequestBody @Valid BarbeiroCriacaoDto novoBarbeiro){
        Barbeiro barbeiroCriado = barbeiroService.criarBarbeiro(novoBarbeiro);

        BarbeiroListagemDto barbeiroListagemDto = BarbeiroMapper.toDto(barbeiroCriado);
        return ResponseEntity.ok(barbeiroListagemDto);
    }


    @Operation(summary = "Listar barbeiros de uma barbearia", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<BarbeiroListagemDto>> listarDaMinhaBarbearia() {
        List<Barbeiro> barbeiros = barbeiroService.listaBarbeirosPorBarbearia();

        List<BarbeiroListagemDto> dto = BarbeiroMapper.toDto(barbeiros);

        if (dto == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Atualizar um barbeiro", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}")
    public ResponseEntity<BarbeiroListagemDto> atualizarBarbeiro(@PathVariable Long id, @RequestBody @Valid BarbeiroCriacaoDto novoBarbeiro) {
        Barbeiro barbeiroAtualizado = barbeiroService.atualizarBarbeiro(id, novoBarbeiro);

        if (barbeiroAtualizado == null) return ResponseEntity.notFound().build();

        BarbeiroListagemDto barbeiroListagemDto = BarbeiroMapper.toDto(barbeiroAtualizado);

        return ResponseEntity.ok(barbeiroListagemDto);
    }

    @Operation(summary = "Desativar um barbeiro", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/desativar/{id}")
    public ResponseEntity<BarbeiroDesativacaoDto> desativarBarbeiro(@PathVariable Long id){
        Barbeiro barbeiroDesativado = barbeiroService.desativarBarbeiro(id);

        if(barbeiroDesativado == null){
            return ResponseEntity.status(404).build();
        }
        BarbeiroDesativacaoDto dtoDesativacao = BarbeiroMapper.toDtoDesativacao(barbeiroDesativado);
        return ResponseEntity.status(200).body(dtoDesativacao);
    }

    @Operation(summary = "Logar barbeiro", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/login")
    public ResponseEntity<BarbeiroListagemDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDto autenticar = this.usuarioService.autenticar(usuarioLoginDto);
        Barbeiro barbeiro = barbeiroService.login(autenticar.getUserId());
        return ResponseEntity.ok(BarbeiroMapper.toDto(barbeiro));
    }
}
