package exception;

/**
 *
 * @author DELL
 */
public class ListEmptyException extends RuntimeException{

    public ListEmptyException() {
    }

    public ListEmptyException(String mess) {
        super(mess);
    }    

    public ListEmptyException(String mess, Throwable throwable) {
        super(mess, throwable);
    }
}
