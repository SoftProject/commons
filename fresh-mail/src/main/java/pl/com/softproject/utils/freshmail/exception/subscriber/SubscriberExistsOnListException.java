package pl.com.softproject.utils.freshmail.exception.subscriber;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class SubscriberExistsOnListException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class SubscriberExistsOnListException extends RestException {

    public SubscriberExistsOnListException(int statusCode) {
        super(statusCode);
    }

    public SubscriberExistsOnListException(int statusCode, String message) {
        super(statusCode, message);
    }
}
