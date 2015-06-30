package pl.com.softproject.utils.freshmail.exception.mail;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class IncorrectTagException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class IncorrectTagException extends RestException {

    public IncorrectTagException(int statusCode) {
        super(statusCode);
    }

    public IncorrectTagException(int statusCode, String message) {
        super(statusCode, message);
    }
}
