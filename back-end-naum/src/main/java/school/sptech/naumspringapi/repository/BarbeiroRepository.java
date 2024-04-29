package school.sptech.naumspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.entity.Barbeiro;

import java.util.List;
import java.util.Optional;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
    List<Barbeiro> findByBarbeiroAtivoTrue();

    Optional<Barbeiro> findByUsuarioId(Long id);

    List<Barbeiro> findByBarbeariaIdAndBarbeiroAtivoTrue(Long id);

    Optional<Barbeiro> findById(Long id);
}
