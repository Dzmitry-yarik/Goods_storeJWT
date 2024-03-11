package by.dzmitry.yarashevich.repositories;

import by.dzmitry.yarashevich.dto.UserRegistrationDto;
import by.dzmitry.yarashevich.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String email);
}