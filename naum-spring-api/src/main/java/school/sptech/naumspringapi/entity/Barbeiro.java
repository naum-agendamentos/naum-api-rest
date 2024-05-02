package school.sptech.naumspringapi.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import school.sptech.naumspringapi.domain.usuario.Usuario;

@Entity
@Getter
@Setter
public class Barbeiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String descricao;
    private Boolean barbeiroAtivo;
    private String foto;
    @ManyToOne
    private Barbearia barbearia;
    private Integer fkPermissao;

    @ManyToOne
    private Usuario usuario;



}
