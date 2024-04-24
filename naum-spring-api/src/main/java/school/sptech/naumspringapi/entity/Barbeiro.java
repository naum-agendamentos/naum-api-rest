package school.sptech.naumspringapi.entity;

import jakarta.persistence.*;


@Entity
public class Barbeiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String descricao;
    private boolean barbeiroAtivo;
    private byte[] foto;
    @OneToOne
    private Barbearia barbearia;
    private int fkPermissao;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isBarbeiroAtivo() {
        return barbeiroAtivo;
    }

    public void setBarbeiroAtivo(boolean barbeiroAtivo) {
        this.barbeiroAtivo = barbeiroAtivo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Barbearia getBarbearia() {
        return barbearia;
    }

    public void setBarbearia(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

    public int getFkPermissao() {
        return fkPermissao;
    }

    public void setFkPermissao(int fkPermissao) {
        this.fkPermissao = fkPermissao;
    }
}
