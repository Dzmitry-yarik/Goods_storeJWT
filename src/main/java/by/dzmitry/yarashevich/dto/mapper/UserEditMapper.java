package by.dzmitry.yarashevich.dto.mapper;

import by.dzmitry.yarashevich.dto.UserCreateEditDto;
import by.dzmitry.yarashevich.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserEditMapper implements Mapper<UserCreateEditDto, User>{

    public User map(UserCreateEditDto object, User toObject) {
        copy(object, toObject);
        return toObject;
    }
    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    private static void copy(UserCreateEditDto object, User user) {
        user.setFirstname(object.getFirstname());
        user.setLastname(object.getLastname());
        user.setPassword(object.getPassword());
        user.setUsername(object.getUsername());
    }
}
