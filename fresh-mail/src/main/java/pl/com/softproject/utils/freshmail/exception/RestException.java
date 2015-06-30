package pl.com.softproject.utils.freshmail.exception;

/**
 * Class RestException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class RestException extends RuntimeException {

    private int statusCode;

    public RestException(int statusCode) {
        this.statusCode = statusCode;
    }

    public RestException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return String.format("status code: [%s], message: [%s]", statusCode, super.getMessage());
    }
}
