package school.sptech.naumspringapi.dto.servicoDto;

import lombok.Data;

@Data
public class ServicoQtdMesListagemDto {
    private Long id;
    private String nome;
    private Integer qtdMes;

    public ServicoQtdMesListagemDto(Long id, String nome, Integer qtdMes) {
        this.id = id;
        this.nome = nome;
        this.qtdMes = qtdMes;
    }
}
