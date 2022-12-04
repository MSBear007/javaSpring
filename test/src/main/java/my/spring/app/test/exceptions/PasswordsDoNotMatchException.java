package my.spring.app.test.exceptions;

public class PasswordsDoNotMatchException extends Exception{
    public PasswordsDoNotMatchException(String msg) {
        super(msg);
    }
    public PasswordsDoNotMatchException() {
        super();
    }
}
