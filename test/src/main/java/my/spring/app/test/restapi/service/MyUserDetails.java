package my.spring.app.test.restapi.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.extern.slf4j.Slf4j;
import my.spring.app.test.restapi.model.User;

@Slf4j
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
        List<GrantedAuthority> authorities = user.getRoles()
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
        log.info(authorities.toString());
            return authorities;
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
