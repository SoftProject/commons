package pl.com.softproject.utils.freshmail.dto.response;

import java.util.List;

/**
 * Class SubscriberErrorResponse.
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class SubscriberErrorResponse extends ErrorResponse {

    private SubscriberDataResponse data;

    public SubscriberErrorResponse() {
    }

    public SubscriberErrorResponse(final String status, final List<BasicError> errors) {
        super(status, errors);
    }

    public SubscriberDataResponse getData() {
        return data;
    }

    public void setData(final SubscriberDataResponse data) {
        this.data = data;
    }
}
