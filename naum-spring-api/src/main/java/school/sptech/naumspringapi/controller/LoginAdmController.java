package school.sptech.naumspringapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.entity.LoginAdm;
import school.sptech.naumspringapi.mapper.LoginAdmMapper;
import school.sptech.naumspringapi.service.LoginAdmService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import school.sptech.naumspringapi.dto.loginAdmDto.LoginAdmAtualizacaoDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login-adm")
public class LoginAdmController {

    private final LoginAdmService loginAdmService;

    @ApiOperation("Atualizar LoginAdm.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "LoginAdm atualizado com sucesso!"),
            @ApiResponse(code = 400, message = "Dados inválidos.")
    })
    @Operation(summary = "Atualizar LoginAdm", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping()
    public ResponseEntity<LoginAdmAtualizacaoDto> atualizarLoginAdm(@RequestBody @Valid LoginAdmAtualizacaoDto novoLogin) {
        LoginAdm loginAdmAtualizado = loginAdmService.atualizarLoginAdm(novoLogin);

        if (loginAdmAtualizado == null) return ResponseEntity.notFound().build();

        LoginAdmAtualizacaoDto loginAdmAtualizacaoDto = LoginAdmMapper.toDto(loginAdmAtualizado);

        return ResponseEntity.ok(loginAdmAtualizacaoDto);
    }
}
