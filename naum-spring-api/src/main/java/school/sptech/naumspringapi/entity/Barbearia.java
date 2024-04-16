package school.sptech.naumspringapi.entity;

import jakarta.persistence.*;
import school.sptech.naumspringapi.dto.enderecoDto.EnderecoListagemDto;

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
    @OneToOne
    @JoinColumn(name = "id")
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
}
