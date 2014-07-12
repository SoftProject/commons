package pl.com.softproject.utils.osticket.integration.config;

import pl.com.softproject.utils.osticket.integration.error.ConfigError;

/**
 * Class Config
 *
 * @author Marcin Jasinski <mkjasinski@gmail.com>
 */
public class OsTicketConfig implements Config {

    private String url;
    private String key;

    public OsTicketConfig(String url, String key) {

        if (url.isEmpty()) {
            throw new ConfigError("Illegal value for argument url");
        }
        if (key.isEmpty()) {
            throw new ConfigError("Illegal value for argument key");
        }
        if (url.substring(url.length() - 1).equals("/")) {
            this.url = url.concat("api/tickets.json");
        } else {
            this.url = url.concat("/api/tickets.json");
        }
        this.key = key;
    }

    public String getUrl() {

        return url;
    }

    public String getKey() {

        return key;
    }
}
