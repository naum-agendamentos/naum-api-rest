package school.sptech.naumspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.naumspringapi.entity.Agendamento;
import school.sptech.naumspringapi.entity.AgendamentoServico;

import java.util.List;

public interface AgendamentoServicoRepository extends JpaRepository<AgendamentoServico, Long> {
    List<AgendamentoServico> findByAgendamento(Agendamento agendamento);
}
