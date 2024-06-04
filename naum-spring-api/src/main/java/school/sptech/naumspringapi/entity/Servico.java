package school.sptech.naumspringapi.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeServico;
    private Double preco;
    private Integer tempoServico;
    @ManyToOne
    private Barbearia barbearia;

}
