package pl.com.softproject.utils.freshmail.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ErrorResponse.
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class ErrorResponse implements Serializable {

    private String status;

    private List<BasicError> errors = new ArrayList<>();

    public ErrorResponse() {
    }

    public ErrorResponse(String status, List<BasicError> errors) {
        this.status = status;
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BasicError> getErrors() {
        return errors;
    }

    public void setErrors(List<BasicError> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
               "status='" + status + '\'' +
               ", errors=" + errors +
               '}';
    }
}
