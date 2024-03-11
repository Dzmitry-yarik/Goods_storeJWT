package by.dzmitry.yarashevich.controllers.rest;

import by.dzmitry.yarashevich.dto.AuthenticationResponse;
import by.dzmitry.yarashevich.models.User;
import by.dzmitry.yarashevich.security.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationRestController {

    private final AuthenticationService authService;

    public AuthenticationRestController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/api/registration")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/api/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}