package rockyrobin.be.baristajob.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "shifts")
@Getter
@NoArgsConstructor(access = PROTECTED)
//@EqualsAndHashCode(exclude = "id")
public class Shift {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private LocalDate datum;

    private LocalTime startUur;

    private LocalTime eindUur;

    @Enumerated(EnumType.STRING)
    private ShiftRole shiftRol;

    private int maxAantalBaristas;

    @ManyToOne
    @Setter
    private Vestiging vestiging;
    
    @ManyToMany(mappedBy = "shifts")
    private Set<User> baristas = new HashSet<>();

    public Shift(LocalDate datum, LocalTime startUur, LocalTime eindUur,
                 ShiftRole shiftRol, int maxAantalBaristas, Vestiging vestiging) {
        this.datum = datum;
        this.startUur = startUur;
        this.eindUur = eindUur;
        this.shiftRol = shiftRol;
        this.maxAantalBaristas = maxAantalBaristas;
        this.vestiging = vestiging;
    }

    public Set<User> getBaristas() {
        return Collections.unmodifiableSet(baristas);
    }

    public boolean isVol() {
        return baristas.size() >= maxAantalBaristas;
    }

    @Override
    public String toString() {
        return "%s %s-%s (%s) Vestiging: %s".formatted(datum, startUur, eindUur, shiftRol, vestiging);
    }
}