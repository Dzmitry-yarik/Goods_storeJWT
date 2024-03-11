package by.dzmitry.yarashevich.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AuthenticationResponse {
     String token;
     String message;

    public AuthenticationResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}