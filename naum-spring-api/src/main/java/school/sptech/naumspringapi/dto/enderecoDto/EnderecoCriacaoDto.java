package school.sptech.naumspringapi.dto.enderecoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EnderecoCriacaoDto {
    @NotNull
    @NotBlank
    private String Cidade;
    @NotNull
    @NotBlank
    private String Rua;
    @NotNull
    @NotBlank
    private String bairro;
    @NotNull
    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;
    @NotNull
    @NotBlank
    private String numero;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 2)
    private String uf;



    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String rua) {
        Rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
