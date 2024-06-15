package school.sptech.naumspringapi.service.usuario.autenticacao.dto;

import school.sptech.naumspringapi.domain.usuario.UsuarioTipo;

public class UsuarioTokenDto {
    private Long userId;
    private String nome;
    private String email;
    private String token;

    private UsuarioTipo tipo;

    public UsuarioTipo getTipo() {
        return tipo;
    }

    public void setTipo(UsuarioTipo tipo) {
        this.tipo = tipo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
