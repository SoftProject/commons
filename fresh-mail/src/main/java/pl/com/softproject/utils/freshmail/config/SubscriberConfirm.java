package pl.com.softproject.utils.freshmail.config;

/**
 * Class SubscriberConfirm
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public enum SubscriberConfirm {

    NOT_CONFIRM(0), CONFIRM(1);

    private final int code;

    SubscriberConfirm(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
