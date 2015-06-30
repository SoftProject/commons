package pl.com.softproject.utils.freshmail.exception.mail;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class MailNotSentException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class MailNotSentException extends RestException {

    public MailNotSentException(int statusCode) {
        super(statusCode);
    }

    public MailNotSentException(int statusCode, String message) {
        super(statusCode, message);
    }
}
