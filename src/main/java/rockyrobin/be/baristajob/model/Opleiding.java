package rockyrobin.be.baristajob.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "opleidingen")
@Getter
@NoArgsConstructor(access = PROTECTED)
//@EqualsAndHashCode(exclude = "id")
public class Opleiding {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String titel;

    private String beschrijving;

    private LocalDate datum;

    private int duurInUren;

    private int maxDeelnemers;

    @ManyToMany
    private Set<Vestiging> vestigingen = new HashSet<>();

    @ManyToMany(mappedBy = "opleidingen")
    private Set<User> deelnemers = new HashSet<>();

    public Opleiding(String titel, String beschrijving, LocalDate datum, int duurInUren, int maxDeelnemers) {
        this.titel = titel;
        this.beschrijving = beschrijving;
        this.datum = datum;
        this.duurInUren = duurInUren;
        this.maxDeelnemers = maxDeelnemers;
    }

    public Set<Vestiging> getVestigingen() {
        return Collections.unmodifiableSet(vestigingen);
    }

    public void addVestiging(Vestiging vestiging) {
        vestigingen.add(vestiging);
    }

    public void removeVestiging(Vestiging vestiging) {
        vestigingen.remove(vestiging);
    }

    public Set<User> getDeelnemers() {
        return Collections.unmodifiableSet(deelnemers);
    }

    public boolean isVolgeboekt() {
        return deelnemers.size() >= maxDeelnemers;
    }

    @Override
    public String toString() {
        return "%s (%s, %du) - %d/%d deelnemers".formatted(titel, datum, duurInUren, deelnemers.size(), maxDeelnemers);
    }
}