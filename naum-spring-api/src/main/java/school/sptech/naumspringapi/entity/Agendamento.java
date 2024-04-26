package school.sptech.naumspringapi.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.List;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dataAgendamento;
    @ManyToOne
    private Barbeiro barbeiro;
    @ManyToOne
    private Cliente cliente;
    @OneToMany
    private List<Servico> servico;
}
