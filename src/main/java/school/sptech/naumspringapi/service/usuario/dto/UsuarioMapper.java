package school.sptech.naumspringapi.service.usuario.dto;

import school.sptech.naumspringapi.domain.usuario.Usuario;
import school.sptech.naumspringapi.service.usuario.autenticacao.dto.UsuarioTokenDto;

public class UsuarioMapper {
    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setTipo(usuarioCriacaoDto.getTipo());
        return usuario;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token) {
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();
        
        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail (usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);
        usuarioTokenDto.setTipo(usuario.getTipo());

        return usuarioTokenDto;
    }


}
