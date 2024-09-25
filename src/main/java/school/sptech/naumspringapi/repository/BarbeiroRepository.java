package school.sptech.naumspringapi.repository;

import org.springframework.stereotype.Repository;
import school.sptech.naumspringapi.domain.usuario.Usuario;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Barbearia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
    List<Barbeiro> findByBarbeiroAtivoTrue();

    Optional<Barbeiro> findByUsuarioId(Long id);

    List<Barbeiro> findByBarbeariaIdAndBarbeiroAtivoTrue(Long id);

    Optional<Barbeiro> findById(Long id);
    List<Barbeiro> findAllByBarbeariaAndBarbeiroAtivo(Barbearia barbearia, boolean ativo);
    Barbeiro findByIdAndBarbeiroAtivoTrue(Long id);

    Barbeiro findByUsuario(Usuario usuario);
}
