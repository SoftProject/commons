package pl.com.softproject.utils.osticket.integration.error;

/**
 * Class ConfigException
 *
 * @author Marcin Jasinski <mkjasinski@gmail.com>
 */
public class ConfigError extends IllegalArgumentException {

    public ConfigError(String s) {

        super(s);
    }
}
