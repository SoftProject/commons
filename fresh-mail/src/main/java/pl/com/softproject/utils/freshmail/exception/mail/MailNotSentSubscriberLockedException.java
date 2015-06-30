package pl.com.softproject.utils.freshmail.exception.mail;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class MailNotSentSubscriberLockedException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class MailNotSentSubscriberLockedException extends RestException {

    public MailNotSentSubscriberLockedException(int statusCode) {
        super(statusCode);
    }

    public MailNotSentSubscriberLockedException(int statusCode, String message) {
        super(statusCode, message);
    }
}
