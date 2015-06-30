package pl.com.softproject.utils.freshmail.exception.mail;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class OutOfEmailLimitException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class OutOfEmailLimitException extends RestException {

    public OutOfEmailLimitException(int statusCode) {
        super(statusCode);
    }

    public OutOfEmailLimitException(int statusCode, String message) {
        super(statusCode, message);
    }
}
