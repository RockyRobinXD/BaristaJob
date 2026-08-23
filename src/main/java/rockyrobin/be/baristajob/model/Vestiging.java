package rockyrobin.be.baristajob.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "vestigingen", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"naam", "stad"})
})
@Getter
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode(exclude = "id")
public class Vestiging {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter(NONE)
    private Long id;

    private String naam;

    private String stad;

    private int aantalZitplaatsen;

    @ManyToMany(mappedBy = "vestigingen")
    private Set<Opleiding> opleidingen = new HashSet<>();

    @OneToMany(mappedBy = "vestiging")
    private Set<Shift> shifts = new HashSet<>();

    @OneToMany(mappedBy = "vestiging")
    private Set<User> baristas = new HashSet<>();

    public Vestiging(String naam, String stad, int aantalZitplaatsen) {
        this.naam = naam;
        this.stad = stad;
        this.aantalZitplaatsen = aantalZitplaatsen;
    }

    public Set<Opleiding> getOpleidingen() {
        return Collections.unmodifiableSet(opleidingen);
    }

    public Set<Shift> getShifts() {
        return Collections.unmodifiableSet(shifts);
    }

    public Set<User> getBaristas() {
        return Collections.unmodifiableSet(baristas);
    }

    @Override
    public String toString() {
        return "%s (%s) - %d zitplaatsen".formatted(naam, stad, aantalZitplaatsen);
    }
}