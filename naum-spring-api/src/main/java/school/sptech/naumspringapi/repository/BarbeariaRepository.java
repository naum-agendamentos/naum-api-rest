package school.sptech.naumspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.entity.Barbearia;

import java.util.List;

@Repository
public interface BarbeariaRepository extends JpaRepository<Barbearia, Integer> {
    List<Barbearia> findAllByAtivaTrue();
}
