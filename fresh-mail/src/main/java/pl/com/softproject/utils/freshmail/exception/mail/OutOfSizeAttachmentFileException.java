package pl.com.softproject.utils.freshmail.exception.mail;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class OutOfSizeAttachmentFileException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class OutOfSizeAttachmentFileException extends RestException {

    public OutOfSizeAttachmentFileException(int statusCode) {
        super(statusCode);
    }

    public OutOfSizeAttachmentFileException(int statusCode, String message) {
        super(statusCode, message);
    }
}
