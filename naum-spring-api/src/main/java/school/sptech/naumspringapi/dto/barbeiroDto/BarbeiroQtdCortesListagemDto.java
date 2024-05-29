package school.sptech.naumspringapi.dto.barbeiroDto;

import lombok.Data;

@Data
public class BarbeiroQtdCortesListagemDto {
    private Long id;
    private String nome;
    private Integer cortes;

    public BarbeiroQtdCortesListagemDto(Long id, String nome, Integer cortes) {
        this.id = id;
        this.nome = nome;
        this.cortes = cortes;
    }
}
