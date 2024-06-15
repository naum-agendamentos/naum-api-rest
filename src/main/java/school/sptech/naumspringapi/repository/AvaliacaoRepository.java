package school.sptech.naumspringapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.entity.Avaliacao;
import school.sptech.naumspringapi.entity.Barbearia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findAllByBarbearia(Barbearia barbearia);
    List<Avaliacao> findAllByCliente(Cliente cliente);
    List<Avaliacao> findAllByQtdEstrela(Integer qtdEstrela);
    List<Avaliacao> findAllByBarbeariaAndQtdEstrela(Barbearia barbearia, Integer qtdEstrela);
    List<Avaliacao> findAllByClienteAndQtdEstrela(Cliente cliente, Integer qtdEstrela);

    @Query("SELECT AVG(a.qtdEstrela) FROM Avaliacao a WHERE a.barbearia.id = :barbeariaId")
    Double mediaAvaliacaoEstrelas(Long barbeariaId);
}
