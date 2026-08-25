package rockyrobin.be.baristajob.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rockyrobin.be.baristajob.service.VestigingService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final VestigingService vestigingService;

    @GetMapping
    public String showHomePage(Model model) {
        model.addAttribute("userRole",
                SecurityContextHolder.getContext().getAuthentication()
                        .getAuthorities().iterator().next()
                        .getAuthority().replace("ROLE_", ""));
        model.addAttribute("vestigingen", vestigingService.getAllVestigingenOverview());
        return "homePage";
    }
}
