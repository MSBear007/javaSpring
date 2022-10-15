package my.spring.app.test.exceptions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import my.spring.app.test.exceptions.serializer.ExceptionSerializer;

@JsonSerialize(using = ExceptionSerializer.class)
public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
