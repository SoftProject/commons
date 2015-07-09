package pl.com.softproject.utils.freshmail.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;

import org.apache.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import pl.com.softproject.utils.freshmail.config.Configuration;
import pl.com.softproject.utils.freshmail.dto.request.EmailMessage;
import pl.com.softproject.utils.freshmail.dto.request.Subscriber;
import pl.com.softproject.utils.freshmail.dto.response.BasicCorrectResponse;
import pl.com.softproject.utils.freshmail.dto.response.SubscribersListResponse;
import pl.com.softproject.utils.freshmail.exception.JsonParsingException;
import pl.com.softproject.utils.freshmail.hash.HashGenerator;
import pl.com.softproject.utils.freshmail.util.ClientUtil;
import pl.com.softproject.utils.freshmail.util.StringUtil;

import java.io.IOException;
import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * Class FreshMailClient
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class FreshMailClient implements Serializable {

    private static final Logger logger = Logger.getLogger(FreshMailClient.class);
    private static final String OK = "ok";
    private static final String ERROR = "error";
    private static final String PING_ACTION = "/rest/ping";
    private static final String SUBSCRIBERS_LIST_ACTION = "/rest/subscribers_list/lists";
    private static final String SUBSCRIBER_ADD_ACTION = "/rest/subscriber/add";
    private static final String MAIL_ACTION = "/rest/mail";

    private final Configuration configuration;

    private final HashGenerator hashGenerator;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Client client;

    private boolean debug;
    private LoggingFilter loggingFilter  = new LoggingFilter(System.out);;

    public FreshMailClient(@NotNull final Configuration configuration,
                           @NotNull final HashGenerator hashGenerator) {

        logger.debug("init FreshMailClient ...");

        this.configuration = configuration;
        this.hashGenerator = hashGenerator;

        DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);

        client = Client.create(defaultClientConfig);
        client.setFollowRedirects(false);

    }

    private static void debug(String message) {
        logger.debug(String.format("fresh-mail rest client - message: [%s]", message));
    }

    private static void error(String message) {
        logger.debug(String.format("fresh-mail rest client - %s", message));
    }

    public boolean ping() {

        debug("ping()");

        WebResource webResource = getWebResource(PING_ACTION);

        String hash = hashGenerator
                .generate(configuration.getApiKey(), PING_ACTION, "", configuration.getApiSecret());

        ClientResponse response = webResource.type(hashGenerator.getContentType())
                .header(configuration.getHttpHeaderForApiKey(), configuration.getApiKey())
                .header(configuration.getHttpHeaderForApiSign(), hash).get(ClientResponse.class);

        try {
            String stringResponse = response.getEntity(String.class);

            ClientUtil.catchStandardException(response.getStatus(), stringResponse, ERROR);
            BasicCorrectResponse basicResponse =
                    objectMapper.readValue(stringResponse, BasicCorrectResponse.class);

            return basicResponse.getStatus().equalsIgnoreCase(OK);
        } catch (IOException e) {
            throw new JsonParsingException(e.getMessage(), e);
        }
    }

    public SubscribersListResponse subscribersList() {

        debug("subscribersList()");

        WebResource webResource = getWebResource(SUBSCRIBERS_LIST_ACTION);

        String hash = hashGenerator.generate(configuration.getApiKey(), SUBSCRIBERS_LIST_ACTION, "",
                                             configuration.getApiSecret());

        ClientResponse response = webResource.type(hashGenerator.getContentType())
                .header(configuration.getHttpHeaderForApiKey(), configuration.getApiKey())
                .header(configuration.getHttpHeaderForApiSign(), hash).get(ClientResponse.class);

        try {
            String stringResponse = response.getEntity(String.class);

            ClientUtil.catchStandardException(response.getStatus(), stringResponse, ERROR);

            return objectMapper.readValue(stringResponse, SubscribersListResponse.class);
        } catch (IOException e) {
            throw new JsonParsingException(e.getMessage(), e);
        }
    }

    public boolean subscriberAdd(Subscriber subscriber) {

        debug("subscriberAdd()");

        WebResource webResource = getWebResource(SUBSCRIBER_ADD_ACTION);

        String postData;
        try {
            postData = StringUtil.toJson(subscriber);
        } catch (JsonProcessingException e) {
            throw new JsonParsingException(e.getMessage(), e);
        }

        String hash = hashGenerator
                .generate(configuration.getApiKey(), SUBSCRIBER_ADD_ACTION, postData,
                          configuration.getApiSecret());

        ClientResponse response = webResource.type(hashGenerator.getContentType())
                .header(configuration.getHttpHeaderForApiKey(), configuration.getApiKey())
                .header(configuration.getHttpHeaderForApiSign(), hash)
                .post(ClientResponse.class, postData);

        String stringResponse = response.getEntity(String.class);

        try {
            ClientUtil.catchStandardException(response.getStatus(), stringResponse, ERROR);
            ClientUtil.catchSubscriberAddException(response.getStatus(), stringResponse, ERROR);
        } catch (IOException e) {
            throw new JsonParsingException(e.getMessage(), e);
        }

        BasicCorrectResponse basicResponse;
        try {
            basicResponse = objectMapper.readValue(stringResponse, BasicCorrectResponse.class);
        } catch (IOException e) {
            throw new JsonParsingException(e.getMessage(), e);
        }

        return basicResponse.getStatus().equalsIgnoreCase(OK);
    }

    public boolean mail(EmailMessage emailMessage) {

        debug("mail()");

        WebResource webResource = getWebResource(MAIL_ACTION);

        String postData;
        try {
            postData = StringUtil.toJson(emailMessage);
        } catch (JsonProcessingException e) {
            throw new JsonParsingException(e.getMessage(), e);
        }

        String hash = hashGenerator.generate(configuration.getApiKey(), MAIL_ACTION, postData,
                                             configuration.getApiSecret());

        ClientResponse response = webResource.type(hashGenerator.getContentType())
                .header(configuration.getHttpHeaderForApiKey(), configuration.getApiKey())
                .header(configuration.getHttpHeaderForApiSign(), hash)
                .post(ClientResponse.class, postData);

        String stringResponse = response.getEntity(String.class);

        try {
            ClientUtil.catchStandardException(response.getStatus(), stringResponse, ERROR);
            ClientUtil.catchMailException(response.getStatus(), stringResponse, ERROR);
        } catch (IOException e) {
            throw new JsonParsingException(e.getMessage(), e);
        }

        BasicCorrectResponse basicResponse;
        try {
            basicResponse = objectMapper.readValue(stringResponse, BasicCorrectResponse.class);
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

    private WebResource getWebResource(String action) {
        return client.resource(StringUtil.concatUrls(configuration.getUrl(), action));
    }

    public void setDebug(boolean debug) {

        this.debug = debug;

        if(debug) {
            logger.debug("debug is enabled");
            client.addFilter(loggingFilter);
        } else {
            logger.debug("debug is disabled");
            client.removeFilter(loggingFilter);
        }
    }
}
