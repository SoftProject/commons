package pl.com.softproject.utils.freshmail.config;

/**
 * Enum Encoding
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public enum Encoding {

    UTF8("UTF-8"), ISO88592("iso-8859-2");

    private final String encoding;

    Encoding(String encoding) {
        this.encoding = encoding;
    }

    public String getEncoding() {
        return encoding;
    }
}
