package rockyrobin.be.baristajob.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import rockyrobin.be.baristajob.model.*;
import rockyrobin.be.baristajob.repository.OpleidingRepository;
import rockyrobin.be.baristajob.repository.ShiftRepository;
import rockyrobin.be.baristajob.repository.UserRepository;
import rockyrobin.be.baristajob.repository.VestigingRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class InitDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final VestigingRepository vestigingRepository;
    private final OpleidingRepository opleidingRepository;
    private final ShiftRepository shiftRepository;
    private final BCryptPasswordEncoder passwordBCryptEncoder;

    @Override
    public void run(String... args) throws Exception {
        // --- Vestigingen ---
        Vestiging gent = new Vestiging("BaristaJob Gent-Centrum", "Gent", 24);
        Vestiging antwerpen = new Vestiging("BaristaJob Antwerpen-Zuid", "Antwerpen", 18);
        Vestiging leuven = new Vestiging("BaristaJob Leuven-Station", "Leuven", 12);
        vestigingRepository.saveAll(List.of(gent, antwerpen, leuven));

        // --- Opleidingen (elk gekoppeld aan 1 of meerdere vestigingen) ---
        Opleiding latteArt = new Opleiding(
                "Latte Art Basics",
                "Introductie tot het maken van latte art voor beginners.",
                LocalDate.of(2026, 9, 15), 3, 15);
        latteArt.addVestiging(gent);
        latteArt.addVestiging(antwerpen);

        Opleiding baristaBasics = new Opleiding(
                "Barista Basics",
                "Basisopleiding espresso zetten, malen en machineonderhoud.",
                LocalDate.of(2026, 9, 22), 4, 20);
        baristaBasics.addVestiging(gent);
        baristaBasics.addVestiging(leuven);

        Opleiding klantvriendelijkheid = new Opleiding(
                "Klantvriendelijkheid & Kassa",
                "Training rond klantcontact, klachtenbehandeling en het kassasysteem.",
                LocalDate.of(2026, 10, 5), 2, 25);
        klantvriendelijkheid.addVestiging(antwerpen);
        klantvriendelijkheid.addVestiging(leuven);

        opleidingRepository.saveAll(List.of(latteArt, baristaBasics, klantvriendelijkheid));

        // --- Shifts (per vestiging) ---
        Shift shiftGentOchtend = new Shift(
                LocalDate.of(2026, 8, 25), LocalTime.of(8, 0), LocalTime.of(12, 0),
                ShiftRole.BARISTA, 3, gent);
        Shift shiftGentMiddag = new Shift(
                LocalDate.of(2026, 8, 25), LocalTime.of(12, 0), LocalTime.of(16, 0),
                ShiftRole.KASSIER, 2, gent);
        Shift shiftAntwerpenOchtend = new Shift(
                LocalDate.of(2026, 8, 26), LocalTime.of(9, 0), LocalTime.of(14, 0),
                ShiftRole.BARISTA, 2, antwerpen);
        Shift shiftLeuvenVroeg = new Shift(
                LocalDate.of(2026, 8, 27), LocalTime.of(7, 0), LocalTime.of(13, 0),
                ShiftRole.BARISTA, 3, leuven);

        shiftRepository.saveAll(List.of(shiftGentOchtend, shiftGentMiddag, shiftAntwerpenOchtend, shiftLeuvenVroeg));

        // --- Users: 1 admin + 5 barista's ---
        User admin = new User(
                "Sophie", "Van Damme", "sophie.vandamme@baristajob.be",
                passwordBCryptEncoder.encode("BaristaJob"),
                LocalDate.of(1990, 4, 12), "99999999", true, UserRole.ADMIN);

        User emma = new User(
                "Emma", "Peeters", "emma.peeters@baristajob.be",
                passwordBCryptEncoder.encode("BaristaJob"),
                LocalDate.of(2004, 3, 10), "10293847", true, UserRole.BARISTA);
        emma.setVestiging(gent);
        emma.addOpleiding(latteArt);
        emma.addOpleiding(baristaBasics);
        emma.addShift(shiftGentOchtend);

        User lucas = new User(
                "Lucas", "Willems", "lucas.willems@baristajob.be",
                passwordBCryptEncoder.encode("BaristaJob"),
                LocalDate.of(2005, 7, 22), "11223344", true, UserRole.BARISTA);
        lucas.setVestiging(gent);
        lucas.addOpleiding(baristaBasics);
        lucas.addShift(shiftGentOchtend);
        lucas.addShift(shiftGentMiddag);

        User noor = new User(
                "Noor", "El Amrani", "noor.elamrani@baristajob.be",
                passwordBCryptEncoder.encode("BaristaJob"),
                LocalDate.of(2003, 11, 2), "22334455", true, UserRole.BARISTA);
        noor.setVestiging(antwerpen);
        noor.addOpleiding(klantvriendelijkheid);
        noor.addShift(shiftAntwerpenOchtend);

        User finn = new User(
                "Finn", "De Backer", "finn.debacker@baristajob.be",
                passwordBCryptEncoder.encode("BaristaJob"),
                LocalDate.of(2006, 1, 18), "33445566", false, UserRole.BARISTA);
        finn.setVestiging(leuven);
        // inactieve barista: bewust geen shifts of opleidingen

        User yara = new User(
                "Yara", "Haddad", "yara.haddad@baristajob.be",
                passwordBCryptEncoder.encode("BaristaJob"),
                LocalDate.of(2002, 5, 30), "44556677", true, UserRole.BARISTA);
        yara.setVestiging(antwerpen);
        yara.addOpleiding(latteArt);
        yara.addOpleiding(klantvriendelijkheid);

        userRepository.saveAll(List.of(admin, emma, lucas, noor, finn, yara));

        System.out.println("InitDataConfig: seed data ingeladen (" +
                vestigingRepository.count() + " vestigingen, " +
                opleidingRepository.count() + " opleidingen, " +
                shiftRepository.count() + " shifts, " +
                userRepository.count() + " users).");
    }
}
