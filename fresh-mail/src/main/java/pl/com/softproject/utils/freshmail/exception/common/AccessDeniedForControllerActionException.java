package pl.com.softproject.utils.freshmail.exception.common;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class AccessDeniedForControllerActionException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class AccessDeniedForControllerActionException extends RestException {

    public AccessDeniedForControllerActionException(int statusCode) {
        super(statusCode);
    }

    public AccessDeniedForControllerActionException(int statusCode, String message) {
        super(statusCode, message);
    }
}
