package pl.com.softproject.utils.freshmail.exception.mail;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class IncorrectSenderAddressException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class IncorrectSenderAddressException extends RestException {

    public IncorrectSenderAddressException(int statusCode) {
        super(statusCode);
    }

    public IncorrectSenderAddressException(int statusCode, String message) {
        super(statusCode, message);
    }
}
