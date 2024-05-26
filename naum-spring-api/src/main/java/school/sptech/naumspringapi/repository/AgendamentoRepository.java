package school.sptech.naumspringapi.repository;

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
//    List<Agendamento> findByCliente(Cliente cliente);
//    List<Agendamento> findByBarbeiro(Barbeiro barbeiro);
    List<Agendamento> findByBarbeiroId(Long barbeiroId);
    List<Agendamento> findByClienteId(Long clienteId);
}
