package school.sptech.naumspringapi.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
public class Barbeiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String descricao;
    private boolean barbeiroAtivo;
    private String foto;
    @ManyToOne
    private Barbearia barbearia;
    private int fkPermissao;

}
