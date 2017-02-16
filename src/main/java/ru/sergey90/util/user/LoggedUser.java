package ru.sergey90.util.user;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.sergey90.entity.Role;
import ru.sergey90.entity.User;

import java.io.Serializable;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * залогированный пользователь
 * DTO (Entity User for Controller)
 */
public class LoggedUser implements UserDetails, Serializable {
    private int userId;
    private final boolean enabled;
    private final Set<Role> roles;
    private final String encodedPassword;

    public LoggedUser(User user) {
        this.userId = user.getId();
        this.enabled = user.isEnabled();
        this.roles = user.getRoles();
        this.encodedPassword = user.getPassword();
    }

    public static LoggedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object user = auth.getPrincipal();
        return (user instanceof LoggedUser) ? (LoggedUser) user : null;
    }

    public static LoggedUser get() {
        LoggedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }


    @Override
    public Set<Role> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return encodedPassword;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
