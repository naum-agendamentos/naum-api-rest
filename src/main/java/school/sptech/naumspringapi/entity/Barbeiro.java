package school.sptech.naumspringapi.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import school.sptech.naumspringapi.domain.usuario.Usuario;

import java.util.List;

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
    private boolean barbeiroAtivo;
    @Column(length = 1000)
    private String foto;
    @ManyToOne
    private Barbearia barbearia;
    private Integer fkPermissao;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "barbeiro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agendamento> agendamentos;

    @OneToOne
    @JoinColumn(name = "fk_dias_semana", referencedColumnName = "id")
    private Semana semana;


}
