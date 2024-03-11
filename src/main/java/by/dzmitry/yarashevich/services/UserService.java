package by.dzmitry.yarashevich.services;


import by.dzmitry.yarashevich.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {

    List<User> getAll();

    Optional<User> getById(int id);
    void updateUser(User updatedUser);
    void deleteById(int id);
}