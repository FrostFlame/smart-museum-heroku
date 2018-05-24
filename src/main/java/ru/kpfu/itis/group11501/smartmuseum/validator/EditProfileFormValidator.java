package ru.kpfu.itis.group11501.smartmuseum.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.service.UserService;
import ru.kpfu.itis.group11501.smartmuseum.util.EditProfileForm;

import java.util.regex.Pattern;

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
        if (form.getPhotoFile().getSize() != 0){
            if (!form.getPhotoFile().getContentType().split("/")[0].equalsIgnoreCase("image")){
                errors.rejectValue("photoFile", "incorrectTypeFile", "Файл должен быть картинкой");
            }
        }
        if (!form.getName().matches("[а-яА-Я]+") || form.getName().length() < 3){
            errors.rejectValue("name", "incorrectName", "Неправильный ввод имени");
        }
        if (!form.getSurname().matches("[а-яА-Я]+") || form.getSurname().length() < 3){
            errors.rejectValue("surname", "incorrectSurname", "Неправильный ввод фамилии");
        }

        if (!form.getThirdName().equals("")){
            if (!form.getThirdName().matches("[а-яА-Я]+") || form.getThirdName().length() < 5){
                errors.rejectValue("thirdName", "incorrectThirdname", "Неправильный ввод отчества");
            }
        }

        Pattern pattern = Pattern.compile("[a-zA-Z]+.*");
        if (!pattern.matcher(form.getLogin()).matches() || form.getLogin().length() < 4){
            errors.rejectValue("login","incorrectLogin","Неправильный ввод логина");
        }
    }
}
