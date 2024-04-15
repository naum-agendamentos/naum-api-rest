package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.dto.loginAdmDto.LoginAdmCriacaoDto;
import school.sptech.naumspringapi.dto.loginAdmDto.LoginAdmListagemDto;
import school.sptech.naumspringapi.dto.loginAdmDto.LoginAdmMapper;
import school.sptech.naumspringapi.entity.LoginAdm;
import school.sptech.naumspringapi.repository.LoginAdmRepository;

import java.util.Optional;

@RestController
@RequestMapping("/login-adm")
public class LoginAdmController {
    @Autowired
    private LoginAdmRepository loginAdmRepository;

    @PutMapping
    public ResponseEntity<LoginAdmListagemDto> atualizar(@RequestParam("id") int id, @RequestBody @Valid LoginAdmCriacaoDto novoLoginAdm) {
        Optional<LoginAdm> loginAdmOpt = loginAdmRepository.findById(id);

        if (loginAdmOpt.isEmpty()) return ResponseEntity.status(404).build();
        if (loginAdmOpt.get().getSenha() == novoLoginAdm.getSenha()) return ResponseEntity.status(400).build();

        loginAdmOpt.get().setSenha(novoLoginAdm.getSenha());

        LoginAdm loginAdmSalvo = loginAdmRepository.save(loginAdmOpt.get());

        LoginAdmListagemDto listagemDto = LoginAdmMapper.toDto(loginAdmSalvo);
        return ResponseEntity.status(200).body(listagemDto);
    }
}
