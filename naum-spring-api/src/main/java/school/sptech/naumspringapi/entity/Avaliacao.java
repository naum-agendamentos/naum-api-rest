package school.sptech.naumspringapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int qtdEstrela;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Barbearia barbearia;
}
