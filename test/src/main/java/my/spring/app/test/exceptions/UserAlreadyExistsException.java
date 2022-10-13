package my.spring.app.test.exceptions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import my.spring.app.test.exceptions.serializer.ExceptionSerializer;

@JsonSerialize(using=ExceptionSerializer.class)
public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String msg) {
        super(msg);
    }

    public UserAlreadyExistsException() {
        super();
    }
}
