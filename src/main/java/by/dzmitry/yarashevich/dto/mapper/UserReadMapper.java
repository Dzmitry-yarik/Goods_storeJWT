package by.dzmitry.yarashevich.dto.mapper;

import by.dzmitry.yarashevich.dto.UserReadDto;
import by.dzmitry.yarashevich.models.Role;
import by.dzmitry.yarashevich.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {
    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(
                object.getUserid(),
                object.getFirstname(),
                object.getLastname(),
                 object.getPassword(),
                object.getUsername(),
                object.getRole());
    }
}
