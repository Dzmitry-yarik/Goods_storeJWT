package by.dzmitry.yarashevich.dto;

import by.dzmitry.yarashevich.models.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationDto {
    String firstname;
    String lastname;
    String email;
    String password;
    @Enumerated(value = EnumType.STRING)
    Role role;
}