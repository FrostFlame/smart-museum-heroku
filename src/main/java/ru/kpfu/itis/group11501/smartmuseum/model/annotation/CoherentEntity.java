package ru.kpfu.itis.group11501.smartmuseum.model.annotation;

import ru.kpfu.itis.group11501.smartmuseum.model.enums.EntityName;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by volkov on 25.04.2018.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
public @interface CoherentEntity {
    EntityName name();
}
