package org.example._3_1_1_spring_boot.controller;

import org.example._3_1_1_spring_boot.model.User;
import org.example._3_1_1_spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    public UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    private final String errorMessage = "Запоните ВСЕ поля с данными человека.";


    @GetMapping({"/usersList", "/"})
    public String usersList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "usersList";
    }

    @GetMapping("/addUser")
    public String showAddUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping("/addUser")
    public String saveUser(Model model, @ModelAttribute("user") User user) {
        if (user.getName() != null && !user.getName().isEmpty() //
                && user.getLastName() != null && !user.getLastName().isEmpty()
                && user.getEmail() != null && !user.getEmail().isEmpty()) {
            userService.editUser(user); // Выяснил, что можно заменить persist() на merge(), если id объекта = null. Но метод addUser убирать не буду - малоли что ))
            //userService.addUser(user); - можно и так.
            return "redirect:/usersList";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "addUser";
    }


    @GetMapping("/deleteUser")
    public String showDeleteUserPage(@RequestParam Long id) {
        User user = userService.getUserById(id);
        userService.deleteUser(user);
        return "redirect:/usersList";
    }

    @GetMapping("/edit")
    public String editUserForm(Model model, @RequestParam Long id) {
        User oldUser = userService.getUserById(id);
        model.addAttribute("user", oldUser);
        return "editUser";
    }

    @PostMapping("/editUser")
    public String editUser(Model model, @ModelAttribute("user") User editUser) {

        if (editUser.getName() != null && !editUser.getName().isEmpty() //
                && editUser.getLastName() != null && !editUser.getLastName().isEmpty()
                && editUser.getEmail() != null && !editUser.getEmail().isEmpty()) {
            userService.editUser(editUser);
            return "redirect:/usersList";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "editUser";
    }
}
