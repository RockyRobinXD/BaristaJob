package rockyrobin.be.baristajob.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class ShiftRestDTO {
    private Long id;
    private LocalDate datum;
    private LocalTime startUur;
    private LocalTime eindUur;
    private String rol;
    private int maxBaristas;
    private int aantalIngeschreven;
}
