package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.smartmuseum.model.User;

/**
 * Created by Bogdan Popov on 26.03.2018.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByLogin(String login);
    User findByLogin(String login);
}
