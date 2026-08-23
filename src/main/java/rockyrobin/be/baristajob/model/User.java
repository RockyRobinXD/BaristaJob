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
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "studentenkaartNummer")
})
@Getter
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode(exclude = "id")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter(NONE)
    private Long id;

    private String voornaam;

    private String achternaam;

    private String email;

    private LocalDate geboortedatum;

    private String studentenkaartNummer;

    private boolean actief;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private boolean administratiefGeblokkeerd;

    @ManyToOne
    @Setter
    private Vestiging vestiging;

    @ManyToMany
    private Set<Opleiding> opleidingen = new HashSet<>();

    @ManyToMany
    private Set<Shift> shifts = new HashSet<>();

    public User(String voornaam, String achternaam, String email, LocalDate geboortedatum, String studentenkaartNummer, boolean actief, UserRole userRole) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.email = email;
        this.geboortedatum = geboortedatum;
        this.studentenkaartNummer = studentenkaartNummer;
        this.actief = actief;
        this.userRole = userRole;
    }

    public Set<Opleiding> getOpleidingen() {
        return Collections.unmodifiableSet(opleidingen);
    }

    public void addOpleiding(Opleiding opleiding) {
        opleidingen.add(opleiding);
    }

    public void removeOpleiding(Opleiding opleiding) {
        opleidingen.remove(opleiding);
    }

    public Set<Shift> getShifts() {
        return Collections.unmodifiableSet(shifts);
    }

    public void addShift(Shift shift) {
        shifts.add(shift);
    }

    public void removeShift(Shift shift) {
        shifts.remove(shift);
    }

    @Override
    public String toString() {
        return "%s %s (%s) - %s - Vestiging: %s - Shifts: %d - Opleidingen: %d%s".formatted(
                voornaam,
                achternaam,
                email,
                userRole,
                vestiging != null ? vestiging.getNaam() : "geen",
                shifts.size(),
                opleidingen.size(),
                administratiefGeblokkeerd ? " [GEBLOKKEERD]" : ""
        );
    }
}
