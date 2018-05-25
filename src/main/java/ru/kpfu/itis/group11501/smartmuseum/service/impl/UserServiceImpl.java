package ru.kpfu.itis.group11501.smartmuseum.service.impl;

//import javafx.util.Pair;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.group11501.smartmuseum.model.Position;
import ru.kpfu.itis.group11501.smartmuseum.model.Role;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.model.UserDto;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.Action;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.ActionTypeName;
import ru.kpfu.itis.group11501.smartmuseum.repository.UserRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.PositionService;
import ru.kpfu.itis.group11501.smartmuseum.service.RoleService;
import ru.kpfu.itis.group11501.smartmuseum.service.UserService;
import ru.kpfu.itis.group11501.smartmuseum.util.Helpers;

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
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PositionService positionService) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.positionService = positionService;
        this.passwordEncoder = Helpers.getEncoder();
    }

    private User updateIfBlockDate(User user) {
        if (user.getBlockDate() != null && user.getBlockDate().compareTo(new Date()) < 0) {
            user.setStatus(true);
            user.setBlockDate(null);
            userRepository.save(user);
        }
        return user;
    }

    private List<User> updateAllIfBlockDate(List<User> users) {
        for (User user : users) {
            updateIfBlockDate(user);
        }
        return users;
    }

    @Override
    public User getUser(String login) {
        return updateIfBlockDate(userRepository.findOneByLogin(login));
    }

    @Override
    public User getUser(Long id) {
        return updateIfBlockDate(userRepository.findOneById(id));
    }

    @Override
    @Action(name = ActionTypeName.ADD)
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Action(name = ActionTypeName.UPDATE)
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsersByParameters(String searchField, String role, String position, String status) {
        Collection<Role> roles = new ArrayList<>();
        Collection<Position> positions = new ArrayList<>();
        Collection<Boolean> statuses = new ArrayList<>(Arrays.asList(Boolean.TRUE, Boolean.FALSE));
        if (role.equals("all")) {
            roles.addAll(roleService.getAllRoles());
        } else {
            roles.add(roleService.getRole(Long.valueOf(role)));
        }
        if (position.equals("all")) {
            positions.addAll(positionService.getAllPositions());
        } else {
            positions.add(positionService.getPosition(Long.valueOf(position)));
        }
        if (!status.equals("all")) {
            statuses.remove(!Boolean.valueOf(status));
        }
        List<User> users = userRepository.getFilterUsers(roles, positions, statuses, "%" + searchField.toLowerCase() + "%");
        return updateAllIfBlockDate(users);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return updateAllIfBlockDate(users);
    }

    @Override
    public void updateCurrentSession() {
        UserDetails userDetails = this.getUser(Helpers.getCurrentUser().getId());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
    }

    @Override
    public void blockUser(long id, double blockTime) {
        Calendar calendar = Calendar.getInstance();
        if (blockTime < 1) {
            calendar.add(Calendar.MINUTE, (int) (blockTime * 60));
        } else if (blockTime <= 24) {
            calendar.add(Calendar.HOUR_OF_DAY, (int) blockTime);
        } else {
            calendar.add(Calendar.YEAR, 1000);
        }

        Date blockDate = calendar.getTime();

        User blockedUser = getUser(id);
        blockedUser.setBlockDate(blockDate);
        blockedUser.setStatus(false);

        addUser(blockedUser);
    }

    @Override
    public void changePassword(String newPassword, Long id) {
        User editableUser = getUser(id);
        editableUser.setPassword(Helpers.getEncoder().encode(newPassword));
        userService.updateUser(editableUser);
    }

    @Override
    @Action(name = ActionTypeName.DELETE)
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    @Override
    public void unblockUser(Long id) {
        User user = userRepository.findOneById(id);
        user.setBlockDate(null);
        user.setStatus(true);
        userRepository.save(user);
    }
    @Transactional
    @Override
    public AbstractMap.SimpleEntry<User, String> registerNewUserAccount(UserDto accountDto) {

        if (loginExists(accountDto.getLogin())) {
//            throw new EmailExistsException(
//                    "There is an account with that email address:  + accountDto.getEmail());
            return null;
        }
        User user = new User();
        user.setName(accountDto.getName());
        user.setSurname(accountDto.getSurName());
        user.setThirdName(accountDto.getThirdName());
        user.setLogin(accountDto.getLogin());
        user.setRole(roleService.getRole("NORMAL"));
        String password = generatePassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setPosition(positionService.getPosition(accountDto.getPosition()));
        return new AbstractMap.SimpleEntry<User, String>(userRepository.save(user), password);
    }

    private String generatePassword(){
        return RandomStringUtils.randomAlphanumeric(7);
    }

    private boolean loginExists(String login) {
        User user = userRepository.findOneByLogin(login);
        if (user != null) {
            return true;
        }
        return false;
    }
}
