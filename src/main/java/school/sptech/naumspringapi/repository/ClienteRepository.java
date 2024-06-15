package school.sptech.naumspringapi.repository;

import org.springframework.stereotype.Repository;
import school.sptech.naumspringapi.domain.usuario.Usuario;
import school.sptech.naumspringapi.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Boolean existsByEmail(String email);
    Cliente findByUsuario(Usuario usuario);
}
