package school.sptech.naumspringapi.dto.agendamentoDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgendamentoListagemDto {
    private Long id;
    private LocalDateTime dataHoraAgendamento;
    private ClienteListagemDto cliente;
    private BarbeiroListagemDto barbeiro;

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
}
