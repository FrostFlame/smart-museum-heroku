package ru.kpfu.itis.group11501.smartmuseum.security;

import org.springframework.security.core.GrantedAuthority;
import ru.kpfu.itis.group11501.smartmuseum.model.User;

import java.util.Collection;

/**
 * Created by volkov on 19.04.2018.
 */
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private User user;

    public UserDetails(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
