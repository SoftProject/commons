package pl.com.softproject.utils.freshmail.exception;

/**
 * Class ParsingJsonException
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class JsonParsingException extends RuntimeException {

    public JsonParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
