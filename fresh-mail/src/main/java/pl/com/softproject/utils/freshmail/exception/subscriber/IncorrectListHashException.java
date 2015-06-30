package pl.com.softproject.utils.freshmail.exception.subscriber;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class ListHashIncorrectException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class IncorrectListHashException extends RestException {

    public IncorrectListHashException(int statusCode) {
        super(statusCode);
    }

    public IncorrectListHashException(int statusCode, String message) {
        super(statusCode, message);
    }
}
