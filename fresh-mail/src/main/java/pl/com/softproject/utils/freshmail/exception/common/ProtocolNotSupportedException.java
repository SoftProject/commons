package pl.com.softproject.utils.freshmail.exception.common;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class ProtocolNotSupportedException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class ProtocolNotSupportedException extends RestException {

    public ProtocolNotSupportedException(int statusCode) {
        super(statusCode);
    }

    public ProtocolNotSupportedException(int statusCode, String message) {
        super(statusCode, message);
    }
}
