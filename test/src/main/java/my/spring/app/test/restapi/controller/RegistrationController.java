package my.spring.app.test.restapi.controller;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.spring.app.test.dto.UserDto;
import my.spring.app.test.exceptions.PasswordsDoNotMatchException;
import my.spring.app.test.exceptions.UserAlreadyExistsException;
import my.spring.app.test.restapi.model.User;
import my.spring.app.test.restapi.service.RegistrationService;

@RestController
@RequestMapping("/reg")
public class RegistrationController {
    
    @Autowired
    private RegistrationService service; 

    @PostMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
    public User register(@Valid @ModelAttribute("user")UserDto userDto, BindingResult result) throws PasswordsDoNotMatchException, UserAlreadyExistsException {
        if (result.hasErrors()) throw new ValidationException(result.getAllErrors().toString());
        return service.registerNewAccount(userDto);
    }
}
