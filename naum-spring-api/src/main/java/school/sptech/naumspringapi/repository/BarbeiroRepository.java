package school.sptech.naumspringapi.repository;

import org.springframework.stereotype.Repository;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Barbearia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface BarbeiroRepository extends JpaRepository<Barbeiro, Integer> {
    List<Barbeiro> findAllByBarbeariaAndBarbeiroAtivo(Barbearia barbearia, boolean ativo);
    Barbeiro findByIdAndBarbeiroAtivoTrue(int id);
}
