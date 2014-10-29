package pl.com.softproject.utils.osticket.integration.error;

import java.io.IOException;

/**
 * Class FileToBig
 *
 * @author Marcin Jasinski <mkjasinski@gmail.com>
 */
public class FileToBigError extends IOException {

    public FileToBigError(String message) {

        super(message);
    }
}
