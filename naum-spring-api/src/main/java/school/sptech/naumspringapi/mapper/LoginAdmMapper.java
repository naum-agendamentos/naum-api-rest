package school.sptech.naumspringapi.mapper;

import org.springframework.stereotype.Component;
import school.sptech.naumspringapi.entity.LoginAdm;
import school.sptech.naumspringapi.dto.loginAdmDto.LoginAdmAtualizacaoDto;

import java.util.Objects;

@Component
public class LoginAdmMapper {
    public static LoginAdm toEntity(LoginAdmAtualizacaoDto dto) {
        if(Objects.isNull(dto)) return null;

        LoginAdm loginAdm = new LoginAdm();
        loginAdm.setEmail(dto.getEmail());
        loginAdm.setSenha(dto.getSenha());

        return loginAdm;
    }

    public static LoginAdmAtualizacaoDto toDto(LoginAdm entity) {
        if (Objects.isNull(entity)) return null;

        LoginAdmAtualizacaoDto dto = new LoginAdmAtualizacaoDto();
        dto.setEmail(entity.getEmail());
        dto.setSenha(entity.getSenha());

        return dto;
    }
}