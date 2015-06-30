package pl.com.softproject.utils.freshmail.config;

/**
 * Interface Configuration
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public interface Configuration {

    String getUrl();

    String getHttpHeaderForApiKey();

    String getHttpHeaderForApiSign();

    String getApiKey();

    String getApiSecret();
}
