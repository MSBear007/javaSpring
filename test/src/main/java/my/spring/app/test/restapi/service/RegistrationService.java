package my.spring.app.test.restapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.spring.app.test.dto.UserDto;
import my.spring.app.test.exceptions.PasswordsDoNotMatchException;
import my.spring.app.test.exceptions.UserAlreadyExistsException;
import my.spring.app.test.restapi.model.User;
import my.spring.app.test.restapi.model.UserRole;
import my.spring.app.test.restapi.repositories.UserRepository;
import my.spring.app.test.restapi.repositories.UserRoleRepository;

@Service
@Transactional
public class RegistrationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

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
        List<UserRole> roles = new ArrayList<UserRole>();
        roles.add(userRoleRepository.findByName("CLIENT").get());
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }
}
