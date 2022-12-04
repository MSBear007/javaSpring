package my.spring.app.test.restapi.controller.exception_handling;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import my.spring.app.test.exceptions.PasswordsDoNotMatchException;
import my.spring.app.test.exceptions.ResourceNotFoundException;
import my.spring.app.test.exceptions.UserAlreadyExistsException;
import my.spring.app.test.exceptions.wrapper.ExceptionWrapper;

@RestControllerAdvice
public class RestExceptionHandler {
    
    private String stackTraceString(Exception exc) {
        StringWriter writer = new StringWriter();
        PrintWriter prWriter = new PrintWriter(writer);
        exc.printStackTrace(prWriter);
        return writer.toString();
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ExceptionWrapper> handle1(UserAlreadyExistsException exc) {
        return new ResponseEntity<ExceptionWrapper>(new ExceptionWrapper(exc.getMessage(), null), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PasswordsDoNotMatchException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ExceptionWrapper> handle2(PasswordsDoNotMatchException exc) {
        return new ResponseEntity<ExceptionWrapper>(new ExceptionWrapper(exc.getMessage(), null), HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionWrapper> handle3(ResourceNotFoundException exc) {
        return new ResponseEntity<ExceptionWrapper>(new ExceptionWrapper(exc.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionWrapper> handle4(IOException exc) {
        return new ResponseEntity<ExceptionWrapper>(new ExceptionWrapper(exc.getMessage(), stackTraceString(exc)), HttpStatus.INTERNAL_SERVER_ERROR);    
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionWrapper> handle5(ValidationException exc) {
        return new ResponseEntity<ExceptionWrapper>(new ExceptionWrapper(exc.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    
}
