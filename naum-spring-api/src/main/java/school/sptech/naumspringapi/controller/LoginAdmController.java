//package school.sptech.naumspringapi.controller;
//
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import school.sptech.naumspringapi.dto.loginAdmDto.LoginAdmAtualizarSenhaDto;
//import school.sptech.naumspringapi.mapper.LoginAdmMapper;
//import school.sptech.naumspringapi.entity.LoginAdm;
//import school.sptech.naumspringapi.repository.LoginAdmRepository;
//
//import java.util.Objects;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/login-adm")
//public class LoginAdmController {
//    @Autowired
//    private LoginAdmRepository loginAdmRepository;
//
//    @PutMapping
//    public ResponseEntity<Void> atualizar(@RequestParam("id") int id, @RequestBody @Valid LoginAdmAtualizarSenhaDto novoLoginAdm) {
//        Optional<LoginAdm> loginAdmOpt = loginAdmRepository.findById(id);
//
//        if (loginAdmOpt.isEmpty()) return ResponseEntity.status(404).build();
//        if (Objects.equals(loginAdmOpt.get().getSenha(), novoLoginAdm.getNovaSenha())) return ResponseEntity.status(400).build();
//
//        loginAdmOpt.get().setSenha(novoLoginAdm.getNovaSenha());
//
//        loginAdmRepository.save(loginAdmOpt.get());
//
//        return ResponseEntity.status(200).build();
//    }
//}
