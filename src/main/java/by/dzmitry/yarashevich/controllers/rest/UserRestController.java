package by.dzmitry.yarashevich.controllers.rest;

import by.dzmitry.yarashevich.dto.UserCreateEditDto;
import by.dzmitry.yarashevich.dto.UserReadDto;
import by.dzmitry.yarashevich.models.User;
import by.dzmitry.yarashevich.services.UserServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {

    private final UserServiceDto userServiceDto;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") Integer userId, Model model) {
        try {
            Optional<UserReadDto> userOptional = userServiceDto.findById(userId);
            if (userOptional.isPresent()) {
                UserReadDto userReadDto = userOptional.get();
                return ResponseEntity.ok(userReadDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error retrieving user");
        }
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        try {
            List<UserReadDto> userList = userServiceDto.findAll();
            return ResponseEntity.ok(userList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserCreateEditDto userDto) {
        try {
            userServiceDto.create(userDto);
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Integer userId, @RequestBody UserCreateEditDto userDto) {
        try {
            userServiceDto.update(userId, userDto);
            return ResponseEntity.ok("User updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId) {
        try {
            userServiceDto.delete(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}