package pl.com.softproject.utils.freshmail.dto.response;

/**
 * Class BasicResponse
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class BasicCorrectResponse extends StatusResponse {

    private String data;

    public BasicCorrectResponse() {
    }

    public BasicCorrectResponse(String status, String data) {
        super(status);
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BasicCorrectResponse{" +
               "data='" + data + '\'' +
               '}';
    }
}
