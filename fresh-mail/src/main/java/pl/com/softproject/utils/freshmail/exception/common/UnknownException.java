package pl.com.softproject.utils.freshmail.exception.common;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class UnknownException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class UnknownException extends RestException {

    public UnknownException(int statusCode) {
        super(statusCode);
    }

    public UnknownException(int statusCode, String message) {
        super(statusCode, message);
    }
}
