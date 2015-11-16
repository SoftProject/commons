package pl.com.softproject.utils.freshmail.hash;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Class HtmlHashGeneratorTest.
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
@RunWith(JUnit4.class)
public class HtmlHashGeneratorTest {

    private HtmlHashGenerator htmlHashGenerator = new HtmlHashGenerator();

    @Test
    public void testGetContentType() throws Exception {

        class Configuration {

            private static final String EXPECTED_CONTENT_TYPE = "text/json";
        }

        Assertions.assertThat(htmlHashGenerator.getContentType())
                .isEqualTo(Configuration.EXPECTED_CONTENT_TYPE);
    }

    @Test
    public void testGenerate() throws Exception {

        class Configuration {

            private static final String API_KEY = "API_KEY";
            private static final String GET_DATA = "GET_DATA";
            private static final String POST_DATA = "POST_DATA";
            private static final String API_SECRET = "API_SECRET";
            private static final String EXPECTED_HASH = "120b040a63e49aa66d77652bd35f0252e5d86a0c";
        }

        Assertions.assertThat(htmlHashGenerator
                                      .generate(Configuration.API_KEY, Configuration.GET_DATA,
                                                Configuration.POST_DATA, Configuration.API_SECRET))
                .isEqualTo(Configuration.EXPECTED_HASH);
    }
}
