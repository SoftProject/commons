package pl.com.softproject.utils.freshmail.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import pl.com.softproject.utils.freshmail.config.Configuration;
import pl.com.softproject.utils.freshmail.config.SubscriberConfirm;
import pl.com.softproject.utils.freshmail.config.SubscriberState;
import pl.com.softproject.utils.freshmail.dto.request.EmailMessage;
import pl.com.softproject.utils.freshmail.dto.request.MultipleSubscriber;
import pl.com.softproject.utils.freshmail.dto.request.MultipleSubscribers;
import pl.com.softproject.utils.freshmail.dto.request.Subscriber;
import pl.com.softproject.utils.freshmail.dto.response.BasicCorrectResponse;
import pl.com.softproject.utils.freshmail.dto.response.SubscriberResponse;
import pl.com.softproject.utils.freshmail.dto.response.SubscribersListResponse;
import pl.com.softproject.utils.freshmail.exception.JsonParsingException;
import pl.com.softproject.utils.freshmail.hash.HashGenerator;
import pl.com.softproject.utils.freshmail.util.ClientUtil;
import pl.com.softproject.utils.freshmail.util.StringUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

/**
 * Class FreshMailClient.
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class FreshMailClient implements Serializable {

    public static final int SUBSCRIBERS_MAX_ELEMENTS = 100;
    private static final Logger LOGGER = Logger.getLogger(FreshMailClient.class);
    private static final String OK = "ok";
    private static final String ERROR = "error";
    private static final String PING_ACTION = "/rest/ping";
    private static final String SUBSCRIBERS_LIST_ACTION = "/rest/subscribers_list/lists";
    private static final String SUBSCRIBER_ADD_ACTION = "/rest/subscriber/add";
    private static final String MAIL_ACTION = "/rest/mail";
    private static final String SUBSCRIBERS_ADD_ACTION = "/rest/subscriber/addMultiple";
    private final Configuration configuration;
    private final HashGenerator hashGenerator;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FreshMailClient(@NotNull final Configuration configuration,
                           @NotNull final HashGenerator hashGenerator) {

        LOGGER.debug("init FreshMailClient ...");

        this.configuration = configuration;
        this.hashGenerator = hashGenerator;
    }

    public boolean ping() {

        LOGGER.debug("ping()");

        final RestResponse stringResponse =
                Util.makeRequest(hashGenerator, configuration, PING_ACTION, "", HttpMethod.GET);

        try {
            ClientUtil.catchStandardException(stringResponse.getStatusCode(),
                                              stringResponse.getResponse(), ERROR);
            BasicCorrectResponse basicResponse = objectMapper
                    .readValue(stringResponse.getResponse(), BasicCorrectResponse.class);

            return basicResponse.getStatus().equalsIgnoreCase(OK);
        } catch (IOException e) {
            throw new JsonParsingException(e.getMessage(), e);
        }
    }

    public SubscribersListResponse subscribersList() {

        LOGGER.debug("subscribersList()");

        final RestResponse response =
                Util.makeRequest(hashGenerator, configuration, SUBSCRIBERS_LIST_ACTION, "",
                                 HttpMethod.GET);

        try {
            ClientUtil.catchStandardException(response.getStatusCode(), response.getResponse(),
                                              ERROR);

            return objectMapper.readValue(response.getResponse(), SubscribersListResponse.class);
        } catch (IOException e) {
            throw new JsonParsingException(e.getMessage(), e);
        }
    }

    public boolean subscriberAdd(String listHash, List<Subscriber> list, SubscriberConfirm confirm,
                                 SubscriberState state) {

        for (Subscriber subscriber : list) {
            subscriber.setConfirm(confirm);
            subscriber.setState(state);
            subscriber.setList(listHash);
            subscriberAdd(subscriber);
        }

        return true;
    }

    public boolean subscriberAdd(Subscriber subscriber) {

        LOGGER.debug("subscriberAdd()");

        String postData = Util.getDataForRequest(subscriber);

        final RestResponse response =
                Util.makeRequest(hashGenerator, configuration, SUBSCRIBER_ADD_ACTION, postData,
                                 HttpMethod.POST);

        try {
            ClientUtil.catchStandardException(response.getStatusCode(), response.getResponse(),
                                              ERROR);
            ClientUtil.catchSubscriberAddException(response.getStatusCode(), response.getResponse(),
                                                   ERROR);
        } catch (IOException e) {
            throw new JsonParsingException(e.getMessage(), e);
        }

        BasicCorrectResponse basicResponse;
        try {
            basicResponse =
                    objectMapper.readValue(response.getResponse(), BasicCorrectResponse.class);
        } catch (IOException e) {
            throw new JsonParsingException(e.getMessage(), e);
        }

        return basicResponse.getStatus().equalsIgnoreCase(OK);
    }

    public boolean subscriberAdd(List<Subscriber> subscribers) {

        LOGGER.debug("subscribersAdd(List<Subscriber>)");

        if (subscribers == null || subscribers.size() == 0) {
            return false;
        }

        Subscriber firstSubscriber = subscribers.get(0);
        final int iteration = subscribers.size() / SUBSCRIBERS_MAX_ELEMENTS;

        for (int i = 0; i < iteration + 1; i++) {

            int fromIndex = i * SUBSCRIBERS_MAX_ELEMENTS;
            if (subscribers.size() < fromIndex) {
                continue;
            }

            int toIndex = fromIndex + SUBSCRIBERS_MAX_ELEMENTS - 1;
            if (subscribers.size() < toIndex) {
                toIndex = subscribers.size() - 1;
            }

            List<Subscriber> subscriberList = subscribers.subList(fromIndex, toIndex + 1);

            MultipleSubscribers multipleSubscribers = new MultipleSubscribers();
            multipleSubscribers.setConfirm(firstSubscriber.getConfirm());
            multipleSubscribers.setList(firstSubscriber.getList());

            Set<MultipleSubscriber> multipleSubscriberSet = new LinkedHashSet<>();
            for (final Subscriber subscriber : subscriberList) {
                MultipleSubscriber multipleSubscriber = new MultipleSubscriber();
                multipleSubscriber.setEmail(subscriber.getEmail());

                multipleSubscriberSet.add(multipleSubscriber);
            }

            multipleSubscribers.setSubscribers(multipleSubscriberSet);

            if (!subscribersAdd(multipleSubscribers)) {
                return false;
            }
        }

        return true;
    }

    public boolean subscribersAdd(MultipleSubscribers multipleSubscribers) {

        LOGGER.debug("subscribersAdd(MultipleSubscribers)");

        String postData = Util.getDataForRequest(multipleSubscribers);

        final RestResponse response =
                Util.makeRequest(hashGenerator, configuration, SUBSCRIBERS_ADD_ACTION, postData,
                                 HttpMethod.POST);

        try {
            ClientUtil
                    .catchStandardException(response.getStatusCode(), response.getResponse(), ERROR,
                                            true);
            ClientUtil
                    .catchSubscribersAddException(response.getStatusCode(), response.getResponse(),
                                                  ERROR);
        } catch (IOException e) {
            throw new JsonParsingException(e.getMessage(), e);
        }

        SubscriberResponse basicResponse;
        try {
            basicResponse =
                    objectMapper.readValue(response.getResponse(), SubscriberResponse.class);
        } catch (IOException e) {
            throw new JsonParsingException(e.getMessage(), e);
        }

        return basicResponse.getStatus().equalsIgnoreCase(OK);
    }

    public boolean subscriberAdd(String listHash, Subscriber subscriber, SubscriberConfirm confirm,
                                 SubscriberState state) {

        subscriber.setConfirm(confirm);
        subscriber.setState(state);
        subscriber.setList(listHash);

        return subscriberAdd(subscriber);
    }

    public boolean mail(EmailMessage emailMessage) {

        LOGGER.debug("mail()");

        String postData = Util.getDataForRequest(emailMessage);

        final RestResponse response =
                Util.makeRequest(hashGenerator, configuration, MAIL_ACTION, postData,
                                 HttpMethod.POST);

        try {
            ClientUtil.catchStandardException(response.getStatusCode(), response.getResponse(),
                                              ERROR);
            ClientUtil.catchMailException(response.getStatusCode(), response.getResponse(), ERROR);
        } catch (IOException e) {
            throw new JsonParsingException(e.getMessage(), e);
        }

        BasicCorrectResponse basicResponse;
        try {
            basicResponse =
                    objectMapper.readValue(response.getResponse(), BasicCorrectResponse.class);
        } catch (IOException e) {
            throw new JsonParsingException(e.getMessage(), e);
        }

        return basicResponse.getStatus().equalsIgnoreCase(OK);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public HashGenerator getHashGenerator() {
        return hashGenerator;
    }

    static class RestResponse {

        private final String response;
        private final int statusCode;

        public RestResponse(final String response, final int statusCode) {
            this.response = response;
            this.statusCode = statusCode;
        }

        public String getResponse() {
            return response;
        }

        public int getStatusCode() {
            return statusCode;
        }
    }

    private static class Util {

        private Util() {
        }

        public static String getDataForRequest(Object object) {
            try {
                return StringUtil.toJson(object);
            } catch (JsonProcessingException e) {
                throw new JsonParsingException(e.getMessage(), e);
            }
        }

        private static RestResponse makeRequest(HashGenerator hashGenerator,
                                                Configuration configuration, String actionUrl,
                                                String data, HttpMethod method) {
            String hash = hashGenerator.generate(configuration.getApiKey(), actionUrl, data,
                                                 configuration.getApiSecret());

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(configuration.getHttpHeaderForApiKey(), configuration.getApiKey());
            httpHeaders.add(configuration.getHttpHeaderForApiSign(), hash);
            httpHeaders.setContentType(MediaType.valueOf(hashGenerator.getContentType()));

            HttpEntity<String> entity = new HttpEntity<>(data, httpHeaders);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> exchange = restTemplate
                    .exchange(configuration.getUrl() + actionUrl, method, entity, String.class);

            return new RestResponse(exchange.getBody(), exchange.getStatusCode().value());
        }
    }
}
