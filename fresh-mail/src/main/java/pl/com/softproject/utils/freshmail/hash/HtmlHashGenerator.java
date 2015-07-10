package pl.com.softproject.utils.freshmail.hash;

/**
 * Class HtmlHashGenerator
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class HtmlHashGenerator extends AbstractHashGenerator {

    private static final String CONTENT_TYPE = "text/json";

    @Override
    public String getContentType() {
        return CONTENT_TYPE;
    }


}
