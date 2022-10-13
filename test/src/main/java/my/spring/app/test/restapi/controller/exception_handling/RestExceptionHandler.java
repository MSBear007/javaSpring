package my.spring.app.test.restapi.controller.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import my.spring.app.test.exceptions.PasswordsDoNotMatchException;
import my.spring.app.test.exceptions.UserAlreadyExistsException;

@RestControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public UserAlreadyExistsException handle1(UserAlreadyExistsException exc) {
        return exc;
    }

    @ExceptionHandler(PasswordsDoNotMatchException.class)
    @ResponseStatus(HttpStatus.OK)
    public PasswordsDoNotMatchException handle2(PasswordsDoNotMatchException exc) {
        return exc;
    }
}
