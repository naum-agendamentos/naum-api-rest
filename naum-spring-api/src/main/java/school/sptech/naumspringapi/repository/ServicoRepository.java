package school.sptech.naumspringapi.repository;

import org.springframework.stereotype.Repository;
import school.sptech.naumspringapi.entity.Servico;
import school.sptech.naumspringapi.entity.Barbearia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    List<Servico> findAllByBarbearia(Barbearia barbearia);
    Servico findByIdAndBarbearia(Long id, Barbearia barbearia);
}
