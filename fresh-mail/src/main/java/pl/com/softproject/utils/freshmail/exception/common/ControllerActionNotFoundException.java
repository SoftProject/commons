package pl.com.softproject.utils.freshmail.exception.common;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class ControllerActionNotFoundException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class ControllerActionNotFoundException extends RestException {

    public ControllerActionNotFoundException(int statusCode) {
        super(statusCode);
    }

    public ControllerActionNotFoundException(int statusCode, String message) {
        super(statusCode, message);
    }
}
