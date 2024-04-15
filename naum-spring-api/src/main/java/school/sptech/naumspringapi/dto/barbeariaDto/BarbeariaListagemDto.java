package school.sptech.naumspringapi.dto.barbeariaDto;

import school.sptech.naumspringapi.entity.Endereco;

public class BarbeariaListagemDto {
    private int id;
    private String nome;
    private String linkBarbearia;
    private byte[] fotoBarbearia;
    private Endereco endereco;



    public int getId() {
        return id;
    }

    public void setId(int id) {
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
