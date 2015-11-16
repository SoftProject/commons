package pl.com.softproject.utils.freshmail.hash;

/**
 * Class JsonHashGenerator.
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class JsonHashGenerator extends AbstractHashGenerator {

    private static final String CONTENT_TYPE = "application/json";

    @Override
    public String getContentType() {
        return CONTENT_TYPE;
    }

}
