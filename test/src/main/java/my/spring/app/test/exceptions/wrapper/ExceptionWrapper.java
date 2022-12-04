package my.spring.app.test.exceptions.wrapper;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExceptionWrapper {

    private String message;
    private Date timestamp;
    private String stackTrace;


    public ExceptionWrapper(String message, String stackTrace) {
        this.message = message;
        this.stackTrace = stackTrace;
        this.timestamp = new Date();
    }
}
