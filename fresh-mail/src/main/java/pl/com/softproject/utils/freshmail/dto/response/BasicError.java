package pl.com.softproject.utils.freshmail.dto.response;

import java.io.Serializable;

/**
 * Class BasicError
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class BasicError implements Serializable {

    private String message;

    private Integer code;

    public BasicError() {
    }

    public BasicError(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BasicError{" +
               "message='" + message + '\'' +
               ", code='" + code + '\'' +
               '}';
    }
}
