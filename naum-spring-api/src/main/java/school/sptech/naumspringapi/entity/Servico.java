package school.sptech.naumspringapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeServico;
    private double preco;
    @ManyToOne
    private Barbearia barbearia;
}
