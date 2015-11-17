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
import pl.com.softproject.utils.freshmail.dto.request.EmailMessage;
import pl.com.softproject.utils.freshmail.dto.request.MultipleSubscriber;
import pl.com.softproject.utils.freshmail.dto.request.MultipleSubscribers;
import pl.com.softproject.utils.freshmail.dto.request.Subscriber;
import pl.com.softproject.utils.freshmail.hash.HashGenerator;
import pl.com.softproject.utils.freshmail.hash.JsonHashGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * Class FreshMailClientTest.
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

            private static final String URL = "https://api.freshmail.com/";
            private static final String API_KEY = "API_KEY";
            private static final String API_SECRET = "API_SECRET";
            private static final String HTTP_HEADER_API_KEY = "X-Rest-ApiKey";
            private static final String HTTP_HEADER_API_SIGN = "X-Rest-ApiSign";
        }

        configuration = new FreshMailConfiguration(Configuration.URL, Configuration.API_KEY,
                                                   Configuration.API_SECRET,
                                                   Configuration.HTTP_HEADER_API_KEY,
                                                   Configuration.HTTP_HEADER_API_SIGN);

        freshMailClient = new FreshMailClient(configuration, hashGenerator);
    }

    @Test
    public void testSubscriberListAdd() throws Exception {

        class Configuration {

            public static final String LIST_HASH = "LIST_HASH";
            public static final String EMAIL1 = "admin1@example.com";
            public static final String EMAIL2 = "admin2@example.com";
            public static final String EMAIL3 = "admin3@example.com";
        }

        Subscriber subscriber1 = new Subscriber();
        subscriber1.setEmail(Configuration.EMAIL1);
        subscriber1.setList(Configuration.LIST_HASH);

        Subscriber subscriber2 = new Subscriber();
        subscriber2.setEmail(Configuration.EMAIL2);
        subscriber2.setList(Configuration.LIST_HASH);

        Subscriber subscriber3 = new Subscriber();
        subscriber3.setEmail(Configuration.EMAIL3);
        subscriber3.setList(Configuration.LIST_HASH);

        Assertions.assertThat(
                freshMailClient.subscriberAdd(Arrays.asList(subscriber1, subscriber2, subscriber3)))
                .isTrue();
    }

    @Test
    public void testSubscribersAdd() throws Exception {

        class Configuration {

            public static final String LIST_HASH = "LIST_HASH";
            public static final String EMAIL1 = "admin1@example.com";
            public static final String EMAIL2 = "admin2@example.com";
        }

        MultipleSubscriber multipleSubscriber1 = new MultipleSubscriber(Configuration.EMAIL1);
        MultipleSubscriber multipleSubscriber2 = new MultipleSubscriber(Configuration.EMAIL2);

        MultipleSubscribers multipleSubscribers = new MultipleSubscribers();
        multipleSubscribers.setList(Configuration.LIST_HASH);
        multipleSubscribers.setSubscribers(
                new HashSet<>(Arrays.asList(multipleSubscriber1, multipleSubscriber2)));

        Assertions.assertThat(freshMailClient.subscribersAdd(multipleSubscribers)).isTrue();
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
    public void testSubscriberAddManyParams() throws Exception {

        class Configuration {

            public static final String LIST_HASH = "LIST_HASH";
            public static final String EMAIL = "admin@example.com";
        }

        Subscriber subscriber = new Subscriber();
        subscriber.setEmail(Configuration.EMAIL);
        subscriber.setList(Configuration.LIST_HASH);

        Assertions.assertThat(freshMailClient.subscriberAdd(Configuration.LIST_HASH,
                                                            Collections.singletonList(subscriber),
                                                            SubscriberConfirm.CONFIRM,
                                                            SubscriberState.ACTIVE)).isTrue();
    }

    @Test
    public void testSubscriberAddOne() throws Exception {
        class Configuration {

            public static final String LIST_HASH = "LIST_HASH";
            public static final String EMAIL = "admin@example.com";
        }

        Subscriber subscriber = new Subscriber();
        subscriber.setEmail(Configuration.EMAIL);
        subscriber.setList(Configuration.LIST_HASH);

        Assertions.assertThat(freshMailClient.subscriberAdd(Configuration.LIST_HASH, subscriber,
                                                            SubscriberConfirm.CONFIRM,
                                                            SubscriberState.ACTIVE)).isTrue();
    }

    @Test
    public void testSubscriberAdd() throws Exception {

        class Configuration {

            public static final String LIST_HASH = "LIST_HASH";
            public static final String EMAIL = "admin@example.com";
        }

        Subscriber subscriber = new Subscriber();
        subscriber.setEmail(Configuration.EMAIL);
        subscriber.setConfirm(SubscriberConfirm.CONFIRM);
        subscriber.setList(Configuration.LIST_HASH);
        subscriber.setState(SubscriberState.ACTIVE);

        Assertions.assertThat(freshMailClient.subscriberAdd(subscriber)).isTrue();
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

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setSubscriber(Configuration.SUBSCRIBER);
        emailMessage.setSubject(Configuration.SUBJECT);
        emailMessage.setText(Configuration.TEXT);
        emailMessage.setReplyTo(Configuration.REPLY_TO);
        emailMessage.setFrom(Configuration.FROM);
        emailMessage.setFromName(Configuration.FROM_NAME);

        Assertions.assertThat(freshMailClient.mail(emailMessage)).isTrue();
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
