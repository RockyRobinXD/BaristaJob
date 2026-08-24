package rockyrobin.be.baristajob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rockyrobin.be.baristajob.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Long countUsersByVestiging_Id(Long vestigingId);
}
