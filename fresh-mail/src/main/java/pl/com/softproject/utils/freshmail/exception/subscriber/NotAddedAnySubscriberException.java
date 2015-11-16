package pl.com.softproject.utils.freshmail.exception.subscriber;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class NotAddedAnySubscriberException.
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class NotAddedAnySubscriberException extends RestException {

    public NotAddedAnySubscriberException(final int statusCode) {
        super(statusCode);
    }

    public NotAddedAnySubscriberException(final int statusCode, final String message) {
        super(statusCode, message);
    }
}
