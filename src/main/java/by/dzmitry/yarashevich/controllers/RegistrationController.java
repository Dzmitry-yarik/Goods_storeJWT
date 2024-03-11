package by.dzmitry.yarashevich.controllers;


import by.dzmitry.yarashevich.models.User;
import by.dzmitry.yarashevich.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final AuthenticationService authService;

    public RegistrationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @Autowired
    @ModelAttribute("user")
    public User userRegistrationDto() {
        return new User();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String register(@ModelAttribute("user") User user) {
        try {
            authService.register(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/registration?email_invalid";
        }
        return "redirect:/registration?success";
    }
}