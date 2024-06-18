package school.sptech.naumspringapi.repository;

import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.springframework.stereotype.Repository;
import school.sptech.naumspringapi.entity.Barbearia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface BarbeariaRepository extends JpaRepository<Barbearia, Long> {
    List<Barbearia> findByAtivaTrue();
}
