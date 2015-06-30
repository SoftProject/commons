package pl.com.softproject.utils.freshmail.client;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pl.com.softproject.utils.freshmail.config.Configuration;
import pl.com.softproject.utils.freshmail.config.FreshMailConfiguration;
import pl.com.softproject.utils.freshmail.config.SubscriberConfirm;
import pl.com.softproject.utils.freshmail.config.SubscriberState;
import pl.com.softproject.utils.freshmail.dto.request.AddSubscriber;
import pl.com.softproject.utils.freshmail.dto.request.SendEmail;
import pl.com.softproject.utils.freshmail.hash.HashGenerator;
import pl.com.softproject.utils.freshmail.hash.JsonHashGenerator;

/**
 * Class FreshMailClientTest
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
@RunWith(JUnit4.class)
@Ignore
public class FreshMailClientTest {

    private Configuration configuration;

    private HashGenerator hashGenerator = new JsonHashGenerator();

    private FreshMailClient freshMailClient;

    @Before
    public void before() {

        class Configuration {

            private static final String URL = "API_URL";
            private static final String API_KEY = "API_KEY";
            private static final String API_SECRET = "API_SECRET";
            private static final String HTTP_HEADER_API_KEY = "HEADER_FOR_API_KEY";
            private static final String HTTP_HEADER_API_SIGN = "HEADER_FOR_API_SIGN";
        }

        configuration = new FreshMailConfiguration(Configuration.URL, Configuration.API_KEY,
                                                   Configuration.API_SECRET,
                                                   Configuration.HTTP_HEADER_API_KEY,
                                                   Configuration.HTTP_HEADER_API_SIGN);

        freshMailClient = new FreshMailClient(configuration, hashGenerator);
    }

    @Test
    public void testPing() throws Exception {

        Assertions.assertThat(freshMailClient.ping()).isTrue();
    }

    @Test
    public void testSubscribersList() throws Exception {

        Assertions.assertThat(freshMailClient.subscribersList()).isNotNull();
    }

    @Test
    public void testSubscriberAdd() throws Exception {

        class Configuration {

            public static final String LIST_HASH = "LIST_HASH";
            public static final String EMAIL = "admin@example.com";
        }

        AddSubscriber addSubscriber = new AddSubscriber();
        addSubscriber.setEmail(Configuration.EMAIL);
        addSubscriber.setConfirm(SubscriberConfirm.CONFIRM);
        addSubscriber.setList(Configuration.LIST_HASH);
        addSubscriber.setState(SubscriberState.ACTIVE);

        Assertions.assertThat(freshMailClient.subscriberAdd(addSubscriber)).isTrue();
    }

    @Test
    public void testMail() throws Exception {

        class Configuration {

            public static final String SUBSCRIBER = "admin@example.com";
            public static final String SUBJECT = "test";
            public static final String TEXT = "test";
            public static final String REPLY_TO = SUBSCRIBER;
            public static final String FROM = SUBSCRIBER;
            public static final String FROM_NAME = SUBSCRIBER;
        }

        SendEmail sendEmail = new SendEmail();
        sendEmail.setSubscriber(Configuration.SUBSCRIBER);
        sendEmail.setSubject(Configuration.SUBJECT);
        sendEmail.setText(Configuration.TEXT);
        sendEmail.setReplyTo(Configuration.REPLY_TO);
        sendEmail.setFrom(Configuration.FROM);
        sendEmail.setFromName(Configuration.FROM_NAME);

        Assertions.assertThat(freshMailClient.mail(sendEmail)).isTrue();
    }

    @Test
    public void testGetConfiguration() throws Exception {

        Assertions.assertThat(freshMailClient.getConfiguration()).isEqualTo(configuration);
    }

    @Test
    public void testGetHashGenerator() throws Exception {

        Assertions.assertThat(freshMailClient.getHashGenerator()).isEqualTo(hashGenerator);
    }
}
