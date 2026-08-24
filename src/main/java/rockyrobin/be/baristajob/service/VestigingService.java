package rockyrobin.be.baristajob.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rockyrobin.be.baristajob.dto.response.VestigingOverviewDTO;
import rockyrobin.be.baristajob.model.Vestiging;
import rockyrobin.be.baristajob.repository.UserRepository;
import rockyrobin.be.baristajob.repository.VestigingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VestigingService {
    private UserRepository userRepository;
    private final VestigingRepository vestigingRepository;

    public List<VestigingOverviewDTO> getAllVestigingenOverview() {
        return convertToVestigingOverviewDTOList(vestigingRepository.findAll());
    }

    private List<VestigingOverviewDTO> convertToVestigingOverviewDTOList(List<Vestiging> vestigingen) {
        return vestigingen.stream()
                .map(v -> new VestigingOverviewDTO(v.getId(), v.getNaam(), v.getStad(), v.getAantalZitplaatsen(), v.getBaristas().size()))
                .toList();
    }
}
