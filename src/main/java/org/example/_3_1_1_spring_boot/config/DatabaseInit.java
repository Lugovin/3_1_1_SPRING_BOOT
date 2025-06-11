package org.example._3_1_1_spring_boot.config;

import jakarta.annotation.PostConstruct;
import org.example._3_1_1_spring_boot.model.User;
import org.example._3_1_1_spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit {

    private final UserService userService;

    @Autowired
    public DatabaseInit(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        userService.addUser(new User("Bill", "Gates", "faja@google.com"));
        userService.addUser(new User("John", "Smith", "ddd@mail.ru"));
        userService.addUser(new User("Николай", "Луговин", "lugovin@gmail.com"));

        System.out.println("База данных заполнена начальными данными");
    }
}
