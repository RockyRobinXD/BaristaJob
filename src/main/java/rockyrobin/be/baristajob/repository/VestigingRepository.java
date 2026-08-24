package rockyrobin.be.baristajob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rockyrobin.be.baristajob.model.Vestiging;

import java.util.List;

public interface VestigingRepository extends JpaRepository<Vestiging, Long> {
    List<Vestiging> findAll();
    //long countActiveBaristasByVestigingId(Long vestigingId);
}
