package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.entity.LoginAdm;
import school.sptech.naumspringapi.mapper.LoginAdmMapper;
import school.sptech.naumspringapi.service.LoginAdmService;
import school.sptech.naumspringapi.dto.loginAdmDto.LoginAdmAtualizacaoDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login-adm")
public class LoginAdmController {

    private final LoginAdmService loginAdmService;

    @PutMapping()
    public ResponseEntity<LoginAdmAtualizacaoDto> atualizarLoginAdm(@RequestBody @Valid LoginAdmAtualizacaoDto novoLogin) {
        LoginAdm loginAdmAtualizado = loginAdmService.atualizarLoginAdm(novoLogin);

        if (loginAdmAtualizado == null) return ResponseEntity.notFound().build();

        LoginAdmAtualizacaoDto loginAdmAtualizacaoDto = LoginAdmMapper.toDto(loginAdmAtualizado);

        return ResponseEntity.ok(loginAdmAtualizacaoDto);
    }
}
