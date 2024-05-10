package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.LoginAdm;
import school.sptech.naumspringapi.mapper.LoginAdmMapper;
import school.sptech.naumspringapi.domain.usuario.Usuario;
import school.sptech.naumspringapi.domain.usuario.UsuarioTipo;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.LoginAdmRepository;
import school.sptech.naumspringapi.service.usuario.UsuarioService;
import school.sptech.naumspringapi.exception.NaoEncontradoException;
import school.sptech.naumspringapi.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.naumspringapi.dto.loginAdmDto.LoginAdmAtualizacaoDto;
import school.sptech.naumspringapi.exception.EntidadeImprocessavelException;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginAdmService {

    private final UsuarioService usuarioService;
    private final LoginAdmRepository loginAdmRepository;

    @Transactional
    public LoginAdm atualizarLoginAdm(LoginAdmAtualizacaoDto novoLogin) {
        if (Objects.isNull(novoLogin)) throw new EntidadeImprocessavelException("novoLogin");
        LoginAdm entity = LoginAdmMapper.toEntity(novoLogin);
        entity.setId(1L);

        UsuarioCriacaoDto usuarioCriacaoDto = new UsuarioCriacaoDto();
        usuarioCriacaoDto.setEmail(novoLogin.getEmail());
        usuarioCriacaoDto.setSenha(novoLogin.getSenha());
        usuarioCriacaoDto.setTipo(UsuarioTipo.ADMIN);

        Usuario usuarioAtualizado = usuarioService.atualizar(1L, usuarioCriacaoDto);
        if (Objects.isNull(usuarioAtualizado)) throw new NaoEncontradoException("Usuário atualizado");
        entity.setUsuario(usuarioAtualizado);

        return loginAdmRepository.save(entity);
    }
}
