package school.sptech.naumspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.naumspringapi.entity.Semana;

@Repository
public interface SemanaRepository extends JpaRepository<Semana,Long> {
}
