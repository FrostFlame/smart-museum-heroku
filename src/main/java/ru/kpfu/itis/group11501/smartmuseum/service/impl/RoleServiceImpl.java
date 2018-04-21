package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.smartmuseum.model.Role;
import ru.kpfu.itis.group11501.smartmuseum.repository.RoleRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.RoleService;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(Long roleId) {
        return roleRepository.findOne(roleId);
    }
}
