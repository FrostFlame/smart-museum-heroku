package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.group11501.smartmuseum.model.Position;
import ru.kpfu.itis.group11501.smartmuseum.model.Role;
import ru.kpfu.itis.group11501.smartmuseum.model.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by Bogdan Popov on 26.03.2018.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByLogin(String login);

    @Query("select u from User as u where u.role in ?1 and u.position in ?2 and u.status in ?3" +
            " and (LOWER(concat(u.login, ' ', u.surname, ' ', u.name, ' ', u.thirdName)) like ?4" +
            " or LOWER(concat(u.surname, ' ', u.thirdName)) like ?4 or " +
            "LOWER(concat(u.login, ' ', u.name, ' ', u.surname)) like ?4)")
    List<User> getFilterUsers(Collection<Role> roles, Collection<Position> positions,
                              Collection<Boolean> statuses, String partOfName);
}
