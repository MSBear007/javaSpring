package my.spring.app.test.restapi.controller.exception_handling;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import my.spring.app.test.exceptions.PasswordsDoNotMatchException;
import my.spring.app.test.exceptions.ResourceNotFoundException;
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

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResourceNotFoundException handle3(ResourceNotFoundException exc) {
        return exc;
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public IOException handle4(IOException exc) {
        return exc;
    }

}
