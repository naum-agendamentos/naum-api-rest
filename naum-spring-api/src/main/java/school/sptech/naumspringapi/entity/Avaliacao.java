package school.sptech.naumspringapi.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int qtdEstrela;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Barbearia barbearia;
}
