package school.sptech.naumspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.naumspringapi.entity.Agendamento;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Cliente;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByCliente(Cliente cliente);
    List<Agendamento> findByBarbeiro(Barbeiro barbeiro);
    List<Agendamento> findAllByDataHoraAgendamentoAndCliente(LocalDate dataAgendamento, Cliente cliente);
    List<Agendamento> findAllByDataHoraAgendamentoAndBarbeiro(LocalDate dataAgendamento, Barbeiro barbeiro);

}
