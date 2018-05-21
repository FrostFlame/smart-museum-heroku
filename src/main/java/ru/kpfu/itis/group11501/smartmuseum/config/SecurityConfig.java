package ru.kpfu.itis.group11501.smartmuseum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.impl.MyUserDetailsService;
import ru.kpfu.itis.group11501.smartmuseum.util.Helpers;

import javax.sql.DataSource;

/**
 * Created by Bogdan Popov on 09.11.2017.
 */
@Configuration
@EnableWebSecurity
@ComponentScan("ru.kpfu.itis.group11501.smartmuseum.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final AuthenticationProvider authProvider;
    private final AccessDeniedHandler accessDeniedHandler;
    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(AuthenticationProvider authProvider, AccessDeniedHandler accessDeniedHandler) {
        this.authProvider = authProvider;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/sign_in").anonymous()
                .antMatchers("/").authenticated()
                .antMatchers("/profile/{\\d+}/edit").hasRole("ADMIN")
                .antMatchers("/profile/edit").hasAnyRole("MANAGER", "NORMAL", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                    .rememberMe()
                .and()
                    .logout()
        ;

        http.csrf().disable()
                .formLogin()
                .loginPage("/sign_in")
                .loginProcessingUrl("/login/process")
                .usernameParameter("login")
                .passwordParameter("password")
                .defaultSuccessUrl("/profile", true)
                .failureUrl("/sign_in?error=true")
                .and()
                .logout().logoutSuccessUrl("/sign_in")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(Helpers.getEncoder());
    }

}