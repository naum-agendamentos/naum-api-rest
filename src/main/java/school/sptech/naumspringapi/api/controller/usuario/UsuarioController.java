package school.sptech.naumspringapi.api.controller.usuario;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.naumspringapi.domain.usuario.UsuarioTipo;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.service.BarbeiroService;
import school.sptech.naumspringapi.service.ClienteService;
import school.sptech.naumspringapi.service.usuario.UsuarioService;
import school.sptech.naumspringapi.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.naumspringapi.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.naumspringapi.service.usuario.dto.UsuarioCriacaoDto;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BarbeiroService barbeiroService;

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar (@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto){
        this.usuarioService.criar(usuarioCriacaoDto);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto){
        UsuarioTokenDto usuarioToken = this.usuarioService.autenticar(usuarioLoginDto);

        if(usuarioToken.getTipo() == UsuarioTipo.BARBEIRO){
            boolean isAtivo = barbeiroService.verificarBarbeiroAtivo(barbeiroService.login(usuarioToken.getUserId()));

            if(!isAtivo){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "O usuário foi excluído e não tem mais acesso.");
            }
        }

        return ResponseEntity.status(200).body(usuarioToken);
    }
}
