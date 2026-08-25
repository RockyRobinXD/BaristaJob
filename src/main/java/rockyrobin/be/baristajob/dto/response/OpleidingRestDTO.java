package rockyrobin.be.baristajob.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class OpleidingRestDTO {
    private Long id;
    private String titel;
    private String beschrijving;
    private LocalDate datum;
    private int duur;
    private int maxDeelnemers;
    private int aantalDeelnemers;
}