package school.sptech.naumspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.entity.Servico;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
    List<Servico> findByBarbearia(Barbearia barbearia);
}
