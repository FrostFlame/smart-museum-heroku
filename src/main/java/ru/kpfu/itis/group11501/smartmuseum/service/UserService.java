package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.User;

import java.util.List;

/**
 * Created by Bogdan Popov on 26.03.2018.
 */
public interface UserService {
    User getUser(String login);

    User addUser(User user);

    List<User> getUsersByParameters(String searchField, String role, String position, Boolean status);

    List<User> getAllUsers();
}
