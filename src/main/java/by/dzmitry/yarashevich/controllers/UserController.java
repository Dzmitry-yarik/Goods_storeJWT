package by.dzmitry.yarashevich.controllers;

import by.dzmitry.yarashevich.models.User;
import by.dzmitry.yarashevich.services.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/admin")
@Controller
public class UserController {

     UserServiceImpl UserServiceImpl;

    public UserController(by.dzmitry.yarashevich.services.impl.UserServiceImpl userServiceImpl) {
        UserServiceImpl = userServiceImpl;
    }

    @GetMapping("/users/{userId}")
    public String getUserProfile(@PathVariable("userId") Integer userId, Model model) {
        Optional<User> user = UserServiceImpl.getById(userId);
        model.addAttribute("user", user);
        return "redirect:/profile/" + userId;
    }

    @GetMapping("/users")
    public String getUsersPage(Model model) {
        List<User> userList = UserServiceImpl.getAll();
        model.addAttribute("users", userList);
        return "users";
    }

    @PostMapping("/users/update/{userId}")
    public String update(@PathVariable("userId") Integer userId, @ModelAttribute User user){
        UserServiceImpl.updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{userid}")
    public String deletePersonById(@PathVariable Integer userid) {
        UserServiceImpl.deleteById(userid);
        return "redirect:/admin/users";
    }
}

