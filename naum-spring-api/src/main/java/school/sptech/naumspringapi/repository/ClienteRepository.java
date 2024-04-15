package school.sptech.naumspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.naumspringapi.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
