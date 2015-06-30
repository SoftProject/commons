package pl.com.softproject.utils.freshmail.hash;

/**
 * Interface HashGenerator
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public interface HashGenerator {

    String getContentType();

    String generate(String apiKey, String getData, String postData, String apiSecret);
}
