package school.sptech.naumspringapi.dto.agendamentoDto;

import lombok.Data;
import school.sptech.naumspringapi.entity.Servico;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AgendamentoBloqListagemDto {
    private Long id;
    private LocalDateTime dataHoraAgendamentoInicio;
    private LocalDateTime dataHoraAgendamentoFim;
    private AgendamentoListagemDto.ClienteListagemDto cliente;
    private AgendamentoListagemDto.BarbeiroListagemDto barbeiro;
    private Double valorTotal;
    private List<Servico> servicos;

    @Data
    public static class ClienteListagemDto {
        private Long id;
        private String email;
        private String nome;
        private String telefone;
    }

    @Data
    public static class BarbeiroListagemDto {
        private Long id;
        private String nome;
        private String email;
        private String telefone;
        private String foto;
    }

    @Data
    public static class ServicoListagemDto {
        private Long id;
        private String nome;
        private Double preco;
        private Integer tempoServico;
    }
}
