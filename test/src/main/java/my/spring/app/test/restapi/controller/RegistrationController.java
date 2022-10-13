package my.spring.app.test.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.spring.app.test.dto.UserDto;
import my.spring.app.test.restapi.model.User;
import my.spring.app.test.restapi.repositories.UserRepository;

@RestController
@RequestMapping("/reg")
public class RegistrationController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/")
    public User register(@ModelAttribute("user")UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }
}
