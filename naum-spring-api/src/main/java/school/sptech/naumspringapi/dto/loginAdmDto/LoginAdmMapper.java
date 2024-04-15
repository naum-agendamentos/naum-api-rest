package school.sptech.naumspringapi.dto.loginAdmDto;

import school.sptech.naumspringapi.entity.LoginAdm;

public class LoginAdmMapper {
    public static LoginAdm toEntity(LoginAdmCriacaoDto dto) {
        if(dto == null) return null;

        LoginAdm loginAdm = new LoginAdm();
        loginAdm.setEmail(dto.getEmail());
        loginAdm.setSenha(dto.getSenha());

        return loginAdm;
    }

    public static LoginAdmListagemDto toDto(LoginAdm entity) {
        if (entity == null) return null;

        LoginAdmListagemDto dto = new LoginAdmListagemDto();
        dto.setEmail(entity.getEmail());
        dto.setId(entity.getId());

        return dto;
    }
}
