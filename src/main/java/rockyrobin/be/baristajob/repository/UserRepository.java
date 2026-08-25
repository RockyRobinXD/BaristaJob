package rockyrobin.be.baristajob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rockyrobin.be.baristajob.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Long countUsersByVestiging_Id(Long vestigingId);
    Optional<User> findByEmail(String email);
}
