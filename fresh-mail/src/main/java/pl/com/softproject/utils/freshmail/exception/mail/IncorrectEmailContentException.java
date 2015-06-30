package pl.com.softproject.utils.freshmail.exception.mail;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class IncorrectEmailContentException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class IncorrectEmailContentException extends RestException {

    public IncorrectEmailContentException(int statusCode) {
        super(statusCode);
    }

    public IncorrectEmailContentException(int statusCode, String message) {
        super(statusCode, message);
    }
}
