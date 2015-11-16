package pl.com.softproject.utils.freshmail.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Class SubscriberDataResponse.
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class SubscriberDataResponse implements Serializable {

    private int inserted;

    @JsonProperty("not_inserted")
    private int notInserted;

    private Set<EmailSubscriberErrorResponse> errors = new LinkedHashSet<>();

    public SubscriberDataResponse() {
    }

    public SubscriberDataResponse(final int inserted, final int notInserted,
                                  final Set<EmailSubscriberErrorResponse> errors) {
        this.inserted = inserted;
        this.notInserted = notInserted;
        this.errors = errors;
    }

    public int getInserted() {
        return inserted;
    }

    public void setInserted(final int inserted) {
        this.inserted = inserted;
    }

    public int getNotInserted() {
        return notInserted;
    }

    public void setNotInserted(final int notInserted) {
        this.notInserted = notInserted;
    }

    public Set<EmailSubscriberErrorResponse> getErrors() {
        return errors;
    }

    public void setErrors(final Set<EmailSubscriberErrorResponse> errors) {
        this.errors = errors;
    }
}
