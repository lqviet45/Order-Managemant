package exception;

/**
 *
 * @author DELL
 */
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException() {
    }
    
    public CustomerNotFoundException(String mess) {
        super(mess);
    }
    
    public CustomerNotFoundException(String mess, Throwable throwable) {
        super(mess, throwable);
    }
}
