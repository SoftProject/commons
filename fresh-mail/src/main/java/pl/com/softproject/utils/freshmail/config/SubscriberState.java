package pl.com.softproject.utils.freshmail.config;

/**
 * Class SubscriberState
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public enum SubscriberState {

    ACTIVE(1), TO_ACTIVATE(2), NOT_ACTIVE(3), DISCHARGED(4), SOFT(5), HARD(8);

    private final int code;

    SubscriberState(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
