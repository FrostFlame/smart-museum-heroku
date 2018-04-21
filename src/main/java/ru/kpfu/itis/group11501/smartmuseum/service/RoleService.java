package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRole(Long roleId);
}
