package my.spring.app.test.exceptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String msg) {
        super(msg);
    }

    public UserAlreadyExistsException() {
        super();
    }
}
