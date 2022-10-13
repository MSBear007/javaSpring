package my.spring.app.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.spring.app.test.dto.UserDto;
import my.spring.app.test.exceptions.PasswordsDoNotMatchException;
import my.spring.app.test.exceptions.UserAlreadyExistsException;
import my.spring.app.test.restapi.model.User;
import my.spring.app.test.restapi.repositories.UserRepository;

@Service
@Transactional
public class RegistrationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewAccount(UserDto userDto) throws UserAlreadyExistsException, PasswordsDoNotMatchException {
        if (userRepository.existsByUsername(userDto.getUsername())) 
            throw new UserAlreadyExistsException(userDto.getUsername());
        if (!userDto.getPassword().equals(userDto.getPasswordConfirm()))
            throw new PasswordsDoNotMatchException();
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }
}
