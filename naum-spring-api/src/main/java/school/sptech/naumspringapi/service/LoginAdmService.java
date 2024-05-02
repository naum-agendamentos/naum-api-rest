package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.LoginAdm;
import school.sptech.naumspringapi.mapper.LoginAdmMapper;
import school.sptech.naumspringapi.domain.usuario.Usuario;
import school.sptech.naumspringapi.domain.usuario.UsuarioTipo;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.LoginAdmRepository;
import school.sptech.naumspringapi.service.usuario.UsuarioService;
import school.sptech.naumspringapi.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.naumspringapi.dto.loginAdmDto.LoginAdmAtualizacaoDto;

@Service
@RequiredArgsConstructor
public class LoginAdmService {

    private final LoginAdmRepository loginAdmRepository;
    private final UsuarioService usuarioService;

    @Transactional
    public LoginAdm atualizarLoginAdm(LoginAdmAtualizacaoDto novoLogin) {

        LoginAdm entity = LoginAdmMapper.toEntity(novoLogin);
        entity.setId(Long.valueOf(1));

        UsuarioCriacaoDto usuarioCriacaoDto = new UsuarioCriacaoDto();
        usuarioCriacaoDto.setEmail(novoLogin.getEmail());
        usuarioCriacaoDto.setSenha(novoLogin.getSenha());
        usuarioCriacaoDto.setTipo(UsuarioTipo.ADMIN);

        Usuario usuarioAtualizado = usuarioService.atualizar(Long.valueOf(1), usuarioCriacaoDto);

        if(usuarioAtualizado == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi achado um login desse ADM");
        }

        entity.setUsuario(usuarioAtualizado);

        LoginAdm loginAdm = loginAdmRepository.save(entity);

        return loginAdm;
    }
}
