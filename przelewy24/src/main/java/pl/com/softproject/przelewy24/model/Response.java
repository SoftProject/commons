package pl.com.softproject.przelewy24.model;
/**
 * Copyright 2015-06-23 the original author or authors.
 */

/**
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public class Response {

    private int code;
    private String errorMessage;
    private String token;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Response{" +
               "code=" + code +
               ", errorMessage='" + errorMessage + '\'' +
               ", token='" + token + '\'' +
               '}';
    }
}
