package rockyrobin.be.baristajob.advice;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

	@ModelAttribute("username")
    public String populateUsers(Authentication authentication) {
        return authentication == null ? "" : authentication.getName();
    }
    
}