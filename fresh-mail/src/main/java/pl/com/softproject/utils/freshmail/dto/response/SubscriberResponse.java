package pl.com.softproject.utils.freshmail.dto.response;

import java.io.Serializable;

/**
 * Class ErrorSubscriberResponse.
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class SubscriberResponse implements Serializable {

    private String status;
    private SubscriberDataResponse data;

    public SubscriberResponse() {
    }

    public SubscriberResponse(final String status, final SubscriberDataResponse data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public SubscriberDataResponse getData() {
        return data;
    }

    public void setData(final SubscriberDataResponse data) {
        this.data = data;
    }
}
