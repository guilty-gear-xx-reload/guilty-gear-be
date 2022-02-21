package ggxnet.reload.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

@Controller
@Slf4j
@RequiredArgsConstructor
class RegistrationController {
    private final RegistrationService registrationService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDTO user, Model model, RedirectAttributes redirectAttributes) {
        if (StringUtils.isEmptyOrWhitespace(user.getName()) || StringUtils.isEmptyOrWhitespace(user.getPassword())) {
            model.addAttribute("errorMessage", "Username or password cannot be empty");
            model.addAttribute("user", user);
            return "/registration";
        }
        registrationService.registerUser(user);
        redirectAttributes.addFlashAttribute("userName", user.getName());
        return "redirect:/login";
    }

}
