package ru.kpfu.itis.group11501.smartmuseum.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.itis.group11501.smartmuseum.util.PlayingScheduleAddForm;

/**
 * Created by volkov on 05.05.2018.
 */
@Component
public class PlayingScheduleAddValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return PlayingScheduleAddForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PlayingScheduleAddForm form = (PlayingScheduleAddForm) target;
        if (errors.hasErrors()) return;
        if(form.getBeginTime().compareTo(form.getEndTime())>=0)
            errors.rejectValue("endTime","endTimeBeforeStartTime","Время выключения должно быть после включения");

    }
}
