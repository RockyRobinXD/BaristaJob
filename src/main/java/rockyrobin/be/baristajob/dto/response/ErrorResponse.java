package rockyrobin.be.baristajob.dto.response;

public record ErrorResponse(int status, String message, String timestamp) {}