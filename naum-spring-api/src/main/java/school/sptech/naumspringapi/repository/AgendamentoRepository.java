package school.sptech.naumspringapi.repository;

import org.springframework.stereotype.Repository;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.naumspringapi.entity.Cliente;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    List<Agendamento> findByCliente(Cliente cliente);
    List<Agendamento> findByBarbeiro(Barbeiro barbeiro);
    List<Agendamento> findAllByDataAgendamentoAndCliente(LocalDate dataAgendamento, Cliente cliente);
    List<Agendamento> findAllByDataAgendamentoAndBarbeiro(LocalDate dataAgendamento, Barbeiro barbeiro);

}
