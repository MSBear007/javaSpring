package my.spring.app.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import my.spring.app.test.restapi.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().disable()
            .authorizeHttpRequests((requests) -> requests
                .antMatchers("/").permitAll()
                .antMatchers("/protected/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/**").permitAll())
            .formLogin()
                .permitAll()
                .and()
            .httpBasic().and()
            .logout()
                .permitAll();
                
        return http.build();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }
}
