package by.dzmitry.yarashevich.controllers;

import by.dzmitry.yarashevich.models.User;
import by.dzmitry.yarashevich.dto.AuthenticationResponse;
import by.dzmitry.yarashevich.security.AuthenticationService;
import by.dzmitry.yarashevich.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LoginController {

    AuthenticationService authService;

    UserService userService;

    public LoginController(AuthenticationService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String home() {
        return "profile";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model) {
        AuthenticationResponse response = authService.authenticate(user);
        if (response != null && response.getToken() != null) {
            return "redirect:/profile/" + user.getUserid();
        } else {
            model.addAttribute("error", true);
            return "redirect:/login?error=notfound";
        }
    }

    @GetMapping("/profile/{userid}")
    public String profilePage(@PathVariable("userid") Integer userId, Model model) {
        Optional<User> optionalUser = userService.getById(userId);
        optionalUser.ifPresent(user -> model.addAttribute("user", user));
        return "profile";
    }
}