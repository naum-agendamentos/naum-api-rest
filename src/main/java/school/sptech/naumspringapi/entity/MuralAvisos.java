package school.sptech.naumspringapi.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class MuralAvisos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 500)
    private String descricao;

    @Column(length = 1000)
    private String url;

    private LocalDateTime data;

    @Column(length = 100)
    @Enumerated(EnumType.STRING)
    private TipoAviso tipoAviso;

    @ManyToOne
    @JoinColumn(name = "barbeiro_id")
    private Barbeiro barbeiro;
}
