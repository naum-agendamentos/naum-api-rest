package school.sptech.naumspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.entity.Barbearia;

import java.util.List;

public interface BarbeariaRepository extends JpaRepository<Barbearia, Integer> {
    List<Barbearia> findByAtivaTrue();
}
