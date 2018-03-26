package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.User;

/**
 * Created by Bogdan Popov on 26.03.2018.
 */
public interface UserService {
    User getUser(String login);
}
