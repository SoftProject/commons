package pl.com.softproject.utils.freshmail.exception.mail;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class IncorrectSenderNameException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class IncorrectSenderNameException extends RestException {

    public IncorrectSenderNameException(int statusCode) {
        super(statusCode);
    }

    public IncorrectSenderNameException(int statusCode, String message) {
        super(statusCode, message);
    }
}
