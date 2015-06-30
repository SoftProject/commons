package pl.com.softproject.utils.freshmail.exception.mail;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class IncorrectAttachmentUrlException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class IncorrectAttachmentUrlException extends RestException {

    public IncorrectAttachmentUrlException(int statusCode) {
        super(statusCode);
    }

    public IncorrectAttachmentUrlException(int statusCode, String message) {
        super(statusCode, message);
    }
}
