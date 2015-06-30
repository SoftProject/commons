package pl.com.softproject.utils.freshmail.exception.common;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class AuthorizationFailedException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class AuthorizationFailedException extends RestException {

    public AuthorizationFailedException(int statusCode) {
        super(statusCode);
    }

    public AuthorizationFailedException(int statusCode, String message) {
        super(statusCode, message);
    }
}
