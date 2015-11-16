package pl.com.softproject.utils.freshmail.exception.subscriber;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class NotSetEmailToAddException.
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class NotSetEmailToAddException extends RestException {

    public NotSetEmailToAddException(final int statusCode) {
        super(statusCode);
    }

    public NotSetEmailToAddException(final int statusCode, final String message) {
        super(statusCode, message);
    }
}
