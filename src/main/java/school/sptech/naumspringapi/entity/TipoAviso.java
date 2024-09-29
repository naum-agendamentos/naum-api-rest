package school.sptech.naumspringapi.entity;

public enum TipoAviso {

    INFORMACAO("informacao"),
    ALERTA("alerta"),
    URGENTE("urgente");

    private String descricao;

    TipoAviso(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
