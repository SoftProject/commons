package pl.com.softproject.utils.freshmail.exception.subscriber;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class AdditionalFieldIncorrectException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class IncorrectAdditionalFieldException extends RestException {

    public IncorrectAdditionalFieldException(int statusCode) {
        super(statusCode);
    }

    public IncorrectAdditionalFieldException(int statusCode, String message) {
        super(statusCode, message);
    }
}
