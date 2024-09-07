package school.sptech.naumspringapi.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
public class Barbearia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(length = 1000)
    private String linkBarbearia;
    private Boolean ativa;
    @Column(length = 1000)
    private String fotoBarbearia;

    @ManyToOne
    private Endereco endereco;


}