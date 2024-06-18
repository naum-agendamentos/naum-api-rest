package school.sptech.naumspringapi.repository;

import org.springframework.stereotype.Repository;
import school.sptech.naumspringapi.entity.LoginAdm;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LoginAdmRepository extends JpaRepository<LoginAdm, Long> {
}
