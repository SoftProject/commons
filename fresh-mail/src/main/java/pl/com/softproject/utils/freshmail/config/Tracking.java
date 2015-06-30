package pl.com.softproject.utils.freshmail.config;

/**
 * Enum Tracking
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public enum Tracking {

    OFF(0), ON(1);

    private final int status;

    Tracking(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
