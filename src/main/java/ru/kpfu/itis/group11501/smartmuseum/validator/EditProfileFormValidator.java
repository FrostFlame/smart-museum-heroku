package ru.kpfu.itis.group11501.smartmuseum.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.service.UserService;
import ru.kpfu.itis.group11501.smartmuseum.util.EditProfileForm;

/**
 * Created by volkov on 21.05.2018.
 */
@Component
public class EditProfileFormValidator implements Validator {

    @Autowired
    UserService userService;
    @Override
    public boolean supports(Class<?> aClass) {
        return EditProfileForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EditProfileForm form = (EditProfileForm) target;
        if (errors.hasErrors()) return;
        User user = userService.getUser(form.getLogin());
        if(user!=null && !user.getId().equals(form.getId()))
            errors.rejectValue("login","loginExists","Пользователь с таким логином уже существует");
    }
}
