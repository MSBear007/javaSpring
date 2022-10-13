package my.spring.app.test.exceptions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import my.spring.app.test.exceptions.serializer.ExceptionSerializer;

@JsonSerialize(using=ExceptionSerializer.class)
public class PasswordsDoNotMatchException extends Exception{
    public PasswordsDoNotMatchException(String msg) {
        super(msg);
    }
    public PasswordsDoNotMatchException() {
        super();
    }
}
