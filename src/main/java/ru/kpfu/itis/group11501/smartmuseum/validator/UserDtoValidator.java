package ru.kpfu.itis.group11501.smartmuseum.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.itis.group11501.smartmuseum.model.UserDto;
import ru.kpfu.itis.group11501.smartmuseum.service.UserService;

import java.util.regex.Pattern;

/**
 * Created by volkov on 26.05.2018.
 */
@Component
public class UserDtoValidator implements Validator {

    @Autowired
    UserService userService;
    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto form = (UserDto) target;
        if (errors.hasErrors()) return;
        if(userService.loginExists(form.getLogin()))
            errors.rejectValue("login","loginExists","Пользователь с таким логином уже существует");

        if (!form.getName().matches("[а-яА-Я]+") || form.getName().length() < 3){
            errors.rejectValue("name", "incorrectName", "Неправильный ввод имени");
        }
        if (!form.getSurName().matches("[а-яА-Я]+") || form.getSurName().length() < 3){
            errors.rejectValue("surName", "incorrectSurname", "Неправильный ввод фамилии");
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
