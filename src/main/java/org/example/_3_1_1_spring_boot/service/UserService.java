package org.example._3_1_1_spring_boot.service;

import org.example._3_1_1_spring_boot.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(User user);

    void editUser(User user);

    User getUserById(Long id);
}
