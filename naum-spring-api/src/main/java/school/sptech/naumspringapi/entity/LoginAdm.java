package school.sptech.naumspringapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import school.sptech.naumspringapi.domain.usuario.Usuario;

@Entity
@Getter
@Setter
public class LoginAdm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;

    @ManyToOne
    private Usuario usuario;
}
