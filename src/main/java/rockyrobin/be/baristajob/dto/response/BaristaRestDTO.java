package rockyrobin.be.baristajob.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaristaRestDTO {
    private Long id;
    private String voornaam;
    private String achternaam;
    private String email;
    private String stad;
    private boolean actief;
}