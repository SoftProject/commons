package pl.com.softproject.utils.freshmail.exception.common;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class RequestMethodNotSupportedException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class RequestMethodNotSupportedException extends RestException {

    public RequestMethodNotSupportedException(int statusCode) {
        super(statusCode);
    }

    public RequestMethodNotSupportedException(int statusCode, String message) {
        super(statusCode, message);
    }
}
