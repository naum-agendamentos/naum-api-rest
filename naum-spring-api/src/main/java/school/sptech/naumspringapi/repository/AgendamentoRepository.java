package school.sptech.naumspringapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByBarbeiroId(Long barbeiroId);
    List<Agendamento> findByClienteId(Long clienteId);
    @Query("SELECT DISTINCT a FROM Agendamento a JOIN a.servicosIds s " +
            "WHERE s IN :servicosId AND a.inicio >= :startDate AND a.inicio <= :endDate")
    List<Agendamento> findByServicosIdsContainingAndDateRange(@Param("servicosId") List<Long> servicosId,
                                                              @Param("startDate") LocalDateTime startDate,
                                                              @Param("endDate") LocalDateTime endDate);
    List<Agendamento> findByInicioEquals(LocalDateTime data);

    @Query("SELECT COUNT(a) FROM Agendamento a WHERE DATE(a.inicio) = :dataHoje")
    Integer totalAgendamentoPorData(@Param("dataHoje") LocalDate dataHoje);

}
