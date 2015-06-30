package pl.com.softproject.utils.freshmail.hash;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;

/**
 * Class HtmlHashgenerator
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class HtmlHashGenerator implements Serializable, HashGenerator {

    private static final String CONTENT_TYPE = "text/json";

    @Override
    public String getContentType() {
        return CONTENT_TYPE;
    }

    @Override
    public String generate(String apiKey, String getData, String postData, String apiSecret) {
        return DigestUtils.sha1Hex(apiKey + getData + postData + apiSecret);
    }
}
