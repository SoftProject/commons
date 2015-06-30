package pl.com.softproject.utils.freshmail.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class StringUtil
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class StringUtil {

    private StringUtil() {
    }

    public static String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    public static String concatUrls(String url, String suffix) {
        if (url.endsWith("/")) {
            return String.format("%s%s", url.substring(0, url.length() - 1), suffix);
        }
        return String.format("%s%s", url, suffix);
    }
}
