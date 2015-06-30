package pl.com.softproject.utils.freshmail.exception.mail;

import pl.com.softproject.utils.freshmail.exception.RestException;

/**
 * Class IncorrectDomainForRebrandingException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class IncorrectDomainForRebrandingException extends RestException {

    public IncorrectDomainForRebrandingException(int statusCode) {
        super(statusCode);
    }

    public IncorrectDomainForRebrandingException(int statusCode, String message) {
        super(statusCode, message);
    }
}
