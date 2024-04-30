package school.sptech.naumspringapi.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.domain.usuario.Usuario;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaAtualizacaoDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaCriacaoDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroCriacaoDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroDesativacaoDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.mapper.BarbeiroMapper;
import school.sptech.naumspringapi.service.BarbeiroService;
import school.sptech.naumspringapi.service.usuario.UsuarioService;
import school.sptech.naumspringapi.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.naumspringapi.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.naumspringapi.service.usuario.dto.UsuarioCriacaoDto;

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

    @Operation(summary = "Listar barbeiros", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<BarbeiroListagemDto>> listarBarbeiros() {
        List<Barbeiro> barbeiros = barbeiroService.listarBarbeiros();
        if (barbeiros == null) return ResponseEntity.noContent().build();
        List<BarbeiroListagemDto> barbeiroListagemDtos = BarbeiroMapper.toDto(barbeiros);
        return ResponseEntity.ok(barbeiroListagemDtos);
    }

    @Operation(summary = "Listar barbeiros de uma barbearia", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/local")
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
//
//    @PostMapping("/login")
//    public ResponseEntity<BarbeiroListagemDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
//        UsuarioTokenDto autenticar = this.usuarioService.autenticar(usuarioLoginDto);
//        BarbeiroListagemDto barbeiro = barbeiroService.login(autenticar.getUserId());
//        return ResponseEntity.ok(barbeiro);
//    }
}
