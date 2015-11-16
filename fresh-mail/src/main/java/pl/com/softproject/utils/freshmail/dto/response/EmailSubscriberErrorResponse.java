package pl.com.softproject.utils.freshmail.dto.response;

import java.io.Serializable;

/**
 * Class EmailSubscriberErrorResponse.
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class EmailSubscriberErrorResponse implements Serializable {

    private String email;
    private String error;
    private int code;

    public EmailSubscriberErrorResponse() {
    }

    public EmailSubscriberErrorResponse(final String email, final String error, final int code) {
        this.email = email;
        this.error = error;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(final int code) {
        this.code = code;
    }
}
