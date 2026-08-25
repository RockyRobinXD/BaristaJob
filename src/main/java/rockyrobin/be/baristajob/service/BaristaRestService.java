package rockyrobin.be.baristajob.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rockyrobin.be.baristajob.dto.response.BaristaRestDTO;
import rockyrobin.be.baristajob.dto.response.OpleidingRestDTO;
import rockyrobin.be.baristajob.exception.BaristaNotFoundException;
import rockyrobin.be.baristajob.exception.VestigingNotFoundException;
import rockyrobin.be.baristajob.model.Opleiding;
import rockyrobin.be.baristajob.model.User;
import rockyrobin.be.baristajob.repository.OpleidingRepository;
import rockyrobin.be.baristajob.repository.ShiftRepository;
import rockyrobin.be.baristajob.repository.UserRepository;
import rockyrobin.be.baristajob.repository.VestigingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaristaRestService {

    private final UserRepository userRepository;
    private final ShiftRepository shiftRepository;
    private final OpleidingRepository opleidingRepository;
    private final VestigingRepository vestigingRepository;

    public List<BaristaRestDTO> getBaristasByStad(String stad) {
        return userRepository.findAll().stream()
                .filter(u -> u.getVestiging() != null
                        && u.getVestiging().getStad().equalsIgnoreCase(stad))
                .map(this::toDTO)
                .toList();
    }

    public long getAantalBeschikbareShifts(Long baristaId) {
        User barista = userRepository.findById(baristaId)
                .orElseThrow(() -> new BaristaNotFoundException(baristaId));

        return shiftRepository.findAll().stream()
                .filter(s -> !barista.getShifts().contains(s))
                .filter(s -> s.getBaristas().size() < s.getMaxAantalBaristas())
                .count();
    }

    public List<OpleidingRestDTO> getOpleidingenByVestiging(Long vestigingId) {
        vestigingRepository.findById(vestigingId)
                .orElseThrow(() -> new VestigingNotFoundException(vestigingId));

        return opleidingRepository.findAll().stream()
                .filter(o -> o.getVestigingen().stream()
                        .anyMatch(v -> v.getId().equals(vestigingId)))
                .map(this::toDTO)
                .toList();
    }

    private BaristaRestDTO toDTO(User u) {
        return new BaristaRestDTO(
                u.getId(),
                u.getVoornaam(),
                u.getAchternaam(),
                u.getEmail(),
                u.getVestiging().getStad(),
                u.isActief()
        );
    }

    private OpleidingRestDTO toDTO(Opleiding o) {
        return new OpleidingRestDTO(
                o.getId(),
                o.getTitel(),
                o.getBeschrijving(),
                o.getDatum(),
                o.getDuurInUren(),
                o.getMaxDeelnemers(),
                o.getDeelnemers().size()
        );
    }
}
