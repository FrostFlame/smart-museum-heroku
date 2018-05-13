package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.Action;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.CoherentEntity;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.ActionTypeName;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.EntityName;
import ru.kpfu.itis.group11501.smartmuseum.util.EditProfileForm;

import java.util.List;

/**
 * Created by Bogdan Popov on 26.03.2018.
 */
@CoherentEntity(name = EntityName.USER)
public interface UserService {
    User getUser(String login);

    User getUser(Long id);

    @Action(name = ActionTypeName.ADD)
    User addUser(User user);

    List<User> getUsersByParameters(String searchField, String role, String position, String status);

    List<User> getAllUsers();

    void normalEditProfileAndSave(User editableUser, EditProfileForm editProfileForm);

    void adminEditProfileAndSave(User editableUser, EditProfileForm editProfileForm);

    void blockUser(long id, double blockTime);
}
