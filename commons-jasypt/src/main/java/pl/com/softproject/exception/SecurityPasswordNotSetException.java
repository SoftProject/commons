package pl.com.softproject.exception;

/**
 * @author <a href="mailto:gchas@soft-project.pl">Grzegorz Chaś</a>
 *         $Rev: $, $LastChangedBy: $
 *         $LastChangedDate: $
 */
public class SecurityPasswordNotSetException extends RuntimeException {

    public SecurityPasswordNotSetException() {
    }

    public SecurityPasswordNotSetException(String message) {
        super(message);
    }

    public SecurityPasswordNotSetException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityPasswordNotSetException(Throwable cause) {
        super(cause);
    }

    public SecurityPasswordNotSetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
