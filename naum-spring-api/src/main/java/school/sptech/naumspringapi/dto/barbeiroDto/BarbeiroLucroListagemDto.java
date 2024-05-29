package school.sptech.naumspringapi.dto.barbeiroDto;

import lombok.Data;

@Data
public class BarbeiroLucroListagemDto {
    private Long id;
    private String nome;
    private Double lucro;

    public BarbeiroLucroListagemDto(Long id, String nome, Double lucro) {
        this.id = id;
        this.nome = nome;
        this.lucro = lucro;
    }
}
