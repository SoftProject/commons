package pl.com.softproject.utils.freshmail.exception.mail;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class UnsupportedEncodingException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class UnsupportedEncodingException extends RestException {

    public UnsupportedEncodingException(int statusCode) {
        super(statusCode);
    }

    public UnsupportedEncodingException(int statusCode, String message) {
        super(statusCode, message);
    }
}
