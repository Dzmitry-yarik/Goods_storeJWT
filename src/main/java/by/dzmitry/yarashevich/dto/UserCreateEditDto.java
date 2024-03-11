package by.dzmitry.yarashevich.dto;

import by.dzmitry.yarashevich.models.Role;
import lombok.Data;
import lombok.Value;

@Data
@Value
public class UserCreateEditDto {
    String firstname;
    String lastname;
    String username;
    String password;
    Role role;
}