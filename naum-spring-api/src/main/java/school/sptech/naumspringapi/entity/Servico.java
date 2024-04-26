package school.sptech.naumspringapi.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomeServico;
    private double preco;
    @ManyToOne
    private Barbearia barbearia;
}
