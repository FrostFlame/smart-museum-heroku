package ru.kpfu.itis.group11501.smartmuseum.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kpfu.itis.group11501.smartmuseum.model.User;

import java.util.function.Function;

/**
 * Created by Bogdan Popov on 30.11.2017.
 */
public class UserFormToUserTransformer implements Function<UserForm, User> {

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public User apply(UserForm userForm) {
        User user = new User();
        user.setLogin(userForm.getLogin());
        user.setPassword(encoder.encode(userForm.getPassword()));
        return user;
    }

}
