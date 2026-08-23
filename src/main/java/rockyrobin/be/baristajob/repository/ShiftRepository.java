package rockyrobin.be.baristajob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rockyrobin.be.baristajob.model.Shift;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
