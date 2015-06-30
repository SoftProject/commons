package pl.com.softproject.utils.freshmail.exception.subscriber;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class IncorrectSubscriberStateException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class IncorrectSubscriberStateException extends RestException {

    public IncorrectSubscriberStateException(int statusCode) {
        super(statusCode);
    }

    public IncorrectSubscriberStateException(int statusCode, String message) {
        super(statusCode, message);
    }
}
