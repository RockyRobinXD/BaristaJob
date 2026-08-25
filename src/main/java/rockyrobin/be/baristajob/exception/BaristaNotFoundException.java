package rockyrobin.be.baristajob.exception;

public class BaristaNotFoundException extends RuntimeException {
    public BaristaNotFoundException(Long id) {
        super("Barista niet gevonden met id: %s".formatted(id));
    }
}