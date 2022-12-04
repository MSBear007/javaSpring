package my.spring.app.test.exceptions;

public class ResourceAlreadyExistsException extends Exception {
    public ResourceAlreadyExistsException() {
        super();
    }

    public ResourceAlreadyExistsException(String msg) {
        super(msg);
    }
}
