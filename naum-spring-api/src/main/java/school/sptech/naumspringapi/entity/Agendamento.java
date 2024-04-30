package school.sptech.naumspringapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataHoraAgendamento;
    @ManyToOne
    private Barbeiro barbeiro;
    @ManyToOne
    private Cliente cliente;
    @OneToMany
    private List<Servico> servico;
}
