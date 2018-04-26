package ru.kpfu.itis.group11501.smartmuseum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.kpfu.itis.group11501.smartmuseum.util.UserLogger;

/**
 * Created by volkov on 23.04.2018.
 */
@Configuration
@EnableAspectJAutoProxy
public class LoggerConfig {

    @Bean
    public UserLogger userLogger() {
        return new UserLogger();
    }
}
