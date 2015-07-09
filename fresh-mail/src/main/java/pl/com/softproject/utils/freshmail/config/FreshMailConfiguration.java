package pl.com.softproject.utils.freshmail.config;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * Class FreshMailConfiguration
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class FreshMailConfiguration implements Serializable, Configuration {

    private final String url;

    private final String apiKey;

    private final String apiSecret;

    private final String httHeaderForApiKey;

    private final String httpHeaderForApiSign;

    public FreshMailConfiguration(@NotNull final String apiKey, @NotNull final String apiSecret) {

        this.url = "https://api.freshmail.com/";
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.httHeaderForApiKey = "X-Rest-ApiKey";
        this.httpHeaderForApiSign = "X-Rest-ApiSign";
    }

    public FreshMailConfiguration(@NotNull final String url, @NotNull final String apiKey,
                                  @NotNull final String apiSecret) {

        this.url = url;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.httHeaderForApiKey = "X-Rest-ApiKey";
        this.httpHeaderForApiSign = "X-Rest-ApiSign";
    }

    public FreshMailConfiguration(@NotNull final String url, @NotNull final String apiKey,
                                  @NotNull final String apiSecret,
                                  @NotNull final String httHeaderForApiKey,
                                  @NotNull final String httpHeaderForApiSign) {

        this.url = url;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.httHeaderForApiKey = httHeaderForApiKey;
        this.httpHeaderForApiSign = httpHeaderForApiSign;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getApiKey() {
        return apiKey;
    }

    @Override
    public String getApiSecret() {
        return apiSecret;
    }

    @Override
    public String getHttpHeaderForApiKey() {
        return httHeaderForApiKey;
    }

    @Override
    public String getHttpHeaderForApiSign() {
        return httpHeaderForApiSign;
    }
}
