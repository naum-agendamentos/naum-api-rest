package school.sptech.naumspringapi.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
public class AgendamentoServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgendamentoServico;
    @ManyToOne
    private Agendamento agendamento;
    @ManyToOne
    private Servico servico;
}
