package school.sptech.naumspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.naumspringapi.entity.MuralAvisos;

import java.util.List;

public interface MuralAvisosRepository extends JpaRepository<MuralAvisos, Long> {

    List<MuralAvisos> findByBarbeiroId(Long barbeiroId);
}
