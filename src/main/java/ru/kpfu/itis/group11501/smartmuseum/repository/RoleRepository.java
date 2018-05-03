package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.smartmuseum.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findOneByName(String name);
}
