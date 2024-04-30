package school.sptech.naumspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.naumspringapi.entity.LoginAdm;

@Repository
public interface LoginAdmRepository extends JpaRepository<LoginAdm, Long> {
}
