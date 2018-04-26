package ru.kpfu.itis.group11501.smartmuseum.util;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.reflect.Method;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.group11501.smartmuseum.model.ActionType;
import ru.kpfu.itis.group11501.smartmuseum.model.TableName;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.model.UserStatistic;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.Action;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.CoherentEntity;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.ActionTypeName;
import ru.kpfu.itis.group11501.smartmuseum.model.interfaces.GettingId;
import ru.kpfu.itis.group11501.smartmuseum.service.ActionTypeService;
import ru.kpfu.itis.group11501.smartmuseum.service.TableNameService;
import ru.kpfu.itis.group11501.smartmuseum.service.UserStatisticService;

/**
 * Created by volkov on 23.04.2018.
 */
@Aspect
@Component
public class UserLogger {

    @Autowired
    private TableNameService tableNameService;

    @Autowired
    private ActionTypeService actionTypeService;

    @Autowired
    private UserStatisticService userStatisticService;

    @Around("@annotation(annotation)")
    public Object logServicesCall(ProceedingJoinPoint thisJoinPoint, Action annotation) throws Throwable {

        String className = thisJoinPoint.getSignature().getDeclaringTypeName();
        Object result = thisJoinPoint.proceed();

        User currentUser = Helpers.getCurrentUser();
        ActionType actionType = actionTypeService.getOneByName(annotation.name().toString());
        TableName tableName = tableNameService.getOneByName(Class.forName(className).getAnnotation(CoherentEntity.class).name().toString());
        Date currentTime = new Date();
        Long id;

        if(actionType.getName().equals(ActionTypeName.ADD.name())){
            id =((GettingId)result).getId();
        }
        else{
            if(actionType.getName().equals(ActionTypeName.UPDATE.name())){
                id = ((GettingId)thisJoinPoint.getArgs()[0]).getId();
            }
            else{
                id = (Long)thisJoinPoint.getArgs()[0];
            }
        }

        UserStatistic userStatistic = new UserStatistic(currentUser,actionType,tableName,currentTime,id);
        userStatisticService.addUserStatistic(userStatistic);

        return result;
    }

}
