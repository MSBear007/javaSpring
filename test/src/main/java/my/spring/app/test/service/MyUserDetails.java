package my.spring.app.test.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import my.spring.app.test.restapi.model.User;

public class MyUserDetails implements UserDetails {
    private User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO 
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO 
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO 
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO 
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO 
        return true;
    }
}
