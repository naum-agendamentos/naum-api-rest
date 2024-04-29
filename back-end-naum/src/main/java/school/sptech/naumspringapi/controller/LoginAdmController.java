package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroCriacaoDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;
import school.sptech.naumspringapi.dto.loginAdmDto.LoginAdmAtualizacaoDto;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.LoginAdm;
import school.sptech.naumspringapi.mapper.BarbeiroMapper;
import school.sptech.naumspringapi.mapper.LoginAdmMapper;
import school.sptech.naumspringapi.repository.LoginAdmRepository;
import school.sptech.naumspringapi.service.LoginAdmService;

@RestController
@RequestMapping("/login-adm")
public class LoginAdmController {
    @Autowired
    private LoginAdmRepository loginAdmRepository;

    @Autowired
    private LoginAdmService loginAdmService;

    @PutMapping()
    public ResponseEntity<LoginAdmAtualizacaoDto> atualizarLoginAdm(@RequestBody @Valid LoginAdmAtualizacaoDto novoLogin) {
        LoginAdm loginAdmAtualizado = loginAdmService.atualizarLoginAdm(novoLogin);

        if (loginAdmAtualizado == null) return ResponseEntity.notFound().build();

        LoginAdmAtualizacaoDto loginAdmAtualizacaoDto = LoginAdmMapper.toDto(loginAdmAtualizado);

        return ResponseEntity.ok(loginAdmAtualizacaoDto);
    }
}
