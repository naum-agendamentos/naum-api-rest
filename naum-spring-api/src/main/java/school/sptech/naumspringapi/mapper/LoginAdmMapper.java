//package school.sptech.naumspringapi.mapper;
//
//import school.sptech.naumspringapi.dto.loginAdmDto.LoginAdmAtualizarSenhaDto;
//import school.sptech.naumspringapi.entity.LoginAdm;
//
//public class LoginAdmMapper {
//    public static LoginAdm toEntity(LoginAdmAtualizarSenhaDto dto) {
//        if(dto == null) return null;
//
//        LoginAdm loginAdm = new LoginAdm();
//        loginAdm.setSenha(dto.getNovaSenha());
//
//        return loginAdm;
//    }
//
//    public static boolean toDto(LoginAdm entity) {
//        if (entity == null) return null;
//
//        LoginAdmListagemDto dto = new LoginAdmListagemDto();
//        dto.setEmail(entity.getEmail());
//        dto.setId(entity.getId());
//
//        return dto;
//    }
//}