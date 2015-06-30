package pl.com.softproject.utils.freshmail.config;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Class FreshMailConfigurationTest
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
@RunWith(JUnit4.class)
public class FreshMailConfigurationTest {

    private static final String URL = "API_URL";
    private static final String API_KEY = "API_KEY";
    private static final String API_SECRET = "API_SECRET";
    private static final String HTTP_HEADER_API_KEY = "HEADER_FOR_API_KEY";
    private static final String HTTP_HEADER_API_SIGN = "HEADER_FOR_API_SIGN";

    private final FreshMailConfiguration freshMailConfiguration =
            new FreshMailConfiguration(URL, API_KEY, API_SECRET, HTTP_HEADER_API_KEY,
                                       HTTP_HEADER_API_SIGN);

    @Test
    public void testGetUrl() throws Exception {

        Assertions.assertThat(freshMailConfiguration.getUrl()).isEqualTo(URL);
    }

    @Test
    public void testGetApiKey() throws Exception {

        Assertions.assertThat(freshMailConfiguration.getApiKey()).isEqualTo(API_KEY);
    }

    @Test
    public void testGetHttpHeaderForApiKey() throws Exception {

        Assertions.assertThat(freshMailConfiguration.getHttpHeaderForApiKey())
                .isEqualTo(HTTP_HEADER_API_KEY);
    }

    @Test
    public void testGetHttpHeaderForApiSign() throws Exception {

        Assertions.assertThat(freshMailConfiguration.getHttpHeaderForApiSign())
                .isEqualTo(HTTP_HEADER_API_SIGN);
    }

    @Test
    public void testGetApiSecret() throws Exception {

        Assertions.assertThat(freshMailConfiguration.getApiSecret()).isEqualTo(API_SECRET);
    }
}
