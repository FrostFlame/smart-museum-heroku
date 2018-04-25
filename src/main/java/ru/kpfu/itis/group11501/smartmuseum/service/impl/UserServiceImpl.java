package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.smartmuseum.model.Position;
import ru.kpfu.itis.group11501.smartmuseum.model.Role;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.Action;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.ActionTypeName;
import ru.kpfu.itis.group11501.smartmuseum.repository.UserRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.PositionService;
import ru.kpfu.itis.group11501.smartmuseum.service.RoleService;
import ru.kpfu.itis.group11501.smartmuseum.service.UserService;

import java.util.*;

/**
 * Created by Bogdan Popov on 26.03.2018.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleService roleService;
    private PositionService positionService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PositionService positionService) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.positionService = positionService;
    }

    @Override
    public User getUser(String login) {
        return userRepository.findOneByLogin(login);
    }

    @Override
    @Action(name = ActionTypeName.ADD)
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsersByParameters(String searchField, String role, String position, String status) {
        Collection<Role> roles = new ArrayList<>();
        Collection<Position> positions = new ArrayList<>();
        Collection<Boolean> statuses = new ArrayList<>(Arrays.asList(Boolean.TRUE, Boolean.FALSE));
        Set<User> resultUsers = new HashSet<>();
        if (role.equals("all")) {
            roles.addAll(roleService.getAllRoles());
        } else {
            roles.add(roleService.getRole(Long.valueOf(role)));
        }
        if (position.equals("all")){
            positions.addAll(positionService.getAllPositions());
        } else {
            positions.add(positionService.getPosition(Long.valueOf(position)));
        }
        if (!status.equals("all")){
            statuses.remove(!Boolean.valueOf(status));
        }
        System.out.println(searchField);
        return userRepository.getFilterUsers(roles, positions, statuses, "%" + searchField.toLowerCase() + "%");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
