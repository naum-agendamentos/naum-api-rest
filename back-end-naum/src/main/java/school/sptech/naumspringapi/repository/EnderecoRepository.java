package school.sptech.naumspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.naumspringapi.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
