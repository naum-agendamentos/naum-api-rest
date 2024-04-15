package school.sptech.naumspringapi.dto.barbeariaDto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;
import school.sptech.naumspringapi.entity.Endereco;

public class BarbeariaCriacaoDto {
    @NotNull
    @NotBlank
    private String nome;
    @URL
    @NotNull
    @NotBlank
    private String linkBarbearia;
    @NotNull
    @NotBlank
    private byte[] fotoBarbearia;
    @NotNull
    @NotBlank
    @AssertTrue
    private boolean ativa;
    @NotNull
    @NotBlank
    private Endereco endereco;



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

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
