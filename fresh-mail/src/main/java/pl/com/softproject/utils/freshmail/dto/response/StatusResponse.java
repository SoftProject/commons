package pl.com.softproject.utils.freshmail.dto.response;

import java.io.Serializable;

/**
 * Class StatusResponse
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class StatusResponse implements Serializable {

    private String status;

    public StatusResponse() {
    }

    public StatusResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StatusResponse{" +
               "status='" + status + '\'' +
               '}';
    }
}
