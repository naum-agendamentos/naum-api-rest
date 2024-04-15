package school.sptech.naumspringapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Collection;

@Entity
public class Barbearia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String linkBarbearia;
    private boolean ativa;
    private byte[] fotoBarbearia;
    private Endereco endereco;



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

    public String getLinkBarbearia() {
        return linkBarbearia;
    }

    public void setLinkBarbearia(String linkBarbearia) {
        this.linkBarbearia = linkBarbearia;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public byte[] getFotoBarbearia() {
        return fotoBarbearia;
    }

    public void setFotoBarbearia(byte[] fotoBarbearia) {
        this.fotoBarbearia = fotoBarbearia;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Collection<Servico> getServicos() {
    }
}
