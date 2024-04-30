package school.sptech.naumspringapi.domain.usuario;

public enum UsuarioTipo {

    BARBEIRO("barbeiro"),
    ADMIN("admin"),
    CLIENTE("cliente");

    private String descricao;

    UsuarioTipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
