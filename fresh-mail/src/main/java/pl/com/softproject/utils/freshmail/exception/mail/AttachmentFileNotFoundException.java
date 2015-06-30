package pl.com.softproject.utils.freshmail.exception.mail;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class AttachmentFileNotFoundException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class AttachmentFileNotFoundException extends RestException {

    public AttachmentFileNotFoundException(int statusCode) {
        super(statusCode);
    }

    public AttachmentFileNotFoundException(int statusCode, String message) {
        super(statusCode, message);
    }
}
