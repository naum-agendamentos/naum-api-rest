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
    private String linkBarbearia;
    private Boolean ativa;
    private String fotoBarbearia;

    @ManyToOne
    private Endereco endereco;


}