package my.spring.app.test.exceptions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import my.spring.app.test.exceptions.serializer.ExceptionSerializer;

@JsonSerialize(using = ExceptionSerializer.class)
public class ResourceAlreadyExistsException extends Exception {
    public ResourceAlreadyExistsException() {
        super();
    }

    public ResourceAlreadyExistsException(String msg) {
        super(msg);
    }
}
