package pl.com.softproject.utils.freshmail.exception.mail;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class IncorrectEmailSubjectException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class IncorrectEmailSubjectException extends RestException {

    public IncorrectEmailSubjectException(int statusCode) {
        super(statusCode);
    }

    public IncorrectEmailSubjectException(int statusCode, String message) {
        super(statusCode, message);
    }
}
