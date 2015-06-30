package pl.com.softproject.utils.freshmail.exception.mail;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class IncorrectAddressReplayToException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class IncorrectAddressReplayToException extends RestException {

    public IncorrectAddressReplayToException(int statusCode) {
        super(statusCode);
    }

    public IncorrectAddressReplayToException(int statusCode, String message) {
        super(statusCode, message);
    }
}
