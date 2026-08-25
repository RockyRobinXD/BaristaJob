package rockyrobin.be.baristajob.exception;

public class VestigingNotFoundException extends RuntimeException {
    public VestigingNotFoundException(Long id) {
        super("Vestiging niet gevonden met id: %s".formatted(id));
    }
}