package rockyrobin.be.baristajob.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rockyrobin.be.baristajob.dto.response.BaristaRestDTO;
import rockyrobin.be.baristajob.dto.response.ShiftRestDTO;
import rockyrobin.be.baristajob.dto.response.OpleidingRestDTO;
import rockyrobin.be.baristajob.service.BaristaRestService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BaristaRestController {

    private final BaristaRestService baristaRestService;

    // Ophalen van barista's in een gegeven stad
    @GetMapping("/baristas")
    public List<BaristaRestDTO> getBaristasByStad(@RequestParam String stad) {
        return baristaRestService.getBaristasByStad(stad);
    }

    // Ophalen van aantal beschikbare shifts voor een barista
    @GetMapping("/baristas/{id}/shifts/beschikbaar")
    public long getBeschikbareShifts(@PathVariable Long id) {
        return baristaRestService.getAantalBeschikbareShifts(id);
    }

    // Ophalen van opleidingen per vestiging
    @GetMapping("/vestigingen/{id}/opleidingen")
    public List<OpleidingRestDTO> getOpleidingenByVestiging(@PathVariable Long id) {
        return baristaRestService.getOpleidingenByVestiging(id);
    }
}
