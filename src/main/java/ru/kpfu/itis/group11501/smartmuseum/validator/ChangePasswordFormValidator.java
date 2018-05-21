package ru.kpfu.itis.group11501.smartmuseum.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.itis.group11501.smartmuseum.util.ChangePasswordForm;
import ru.kpfu.itis.group11501.smartmuseum.util.Helpers;

/**
 * Created by volkov on 21.05.2018.
 */
@Component
public class ChangePasswordFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ChangePasswordForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ChangePasswordForm form = (ChangePasswordForm) target;
        if (errors.hasErrors()) return;
        if(!Helpers.getEncoder().matches(form.getCurrentPassword(), Helpers.getCurrentUser().getPassword()))
            errors.rejectValue("currentPassword","passwordWrong","Не верный пароль");
        if(!form.getNewPassword().equals(form.getNewPasswordConf()))
            errors.rejectValue("newPasswordConf","passwordsNotEquals","Пароли не совпадают");
    }
}
