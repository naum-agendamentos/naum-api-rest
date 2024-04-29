package school.sptech.naumspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.naumspringapi.entity.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
}
