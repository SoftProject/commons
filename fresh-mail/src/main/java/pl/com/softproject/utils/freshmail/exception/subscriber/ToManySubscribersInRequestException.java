package pl.com.softproject.utils.freshmail.exception.subscriber;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class ToManySubscribersInRequestException.
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class ToManySubscribersInRequestException extends RestException {

    public ToManySubscribersInRequestException(final int statusCode) {
        super(statusCode);
    }

    public ToManySubscribersInRequestException(final int statusCode, final String message) {
        super(statusCode, message);
    }
}
