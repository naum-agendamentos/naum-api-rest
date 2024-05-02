package school.sptech.naumspringapi.mapper;

import school.sptech.naumspringapi.entity.LoginAdm;
import school.sptech.naumspringapi.dto.loginAdmDto.LoginAdmAtualizacaoDto;

public class LoginAdmMapper {
    public static LoginAdm toEntity(LoginAdmAtualizacaoDto dto) {
        if(dto == null) return null;

        LoginAdm loginAdm = new LoginAdm();
        loginAdm.setEmail(dto.getEmail());
        loginAdm.setSenha(dto.getSenha());

        return loginAdm;
    }

    public static LoginAdmAtualizacaoDto toDto(LoginAdm entity) {
        if (entity == null) return null;

        LoginAdmAtualizacaoDto dto = new LoginAdmAtualizacaoDto();
        dto.setEmail(entity.getEmail());
        dto.setSenha(entity.getSenha());

        return dto;
    }
}