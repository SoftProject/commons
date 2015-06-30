package pl.com.softproject.utils.freshmail.exception.common;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class EmailAddressIncorrectException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class IncorrectEmailAddressException extends RestException {

    public IncorrectEmailAddressException(int statusCode) {
        super(statusCode);
    }

    public IncorrectEmailAddressException(int statusCode, String message) {
        super(statusCode, message);
    }
}
