package my.spring.app.test.exceptions;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
