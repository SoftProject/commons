package pl.com.softproject.przelewy24.service;
/**
 * Copyright 2015-06-23 the original author or authors.
 */

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import pl.com.softproject.przelewy24.model.Currency;
import pl.com.softproject.przelewy24.model.PaymentDetails;
import pl.com.softproject.przelewy24.model.Response;
import pl.com.softproject.przelewy24.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
@Service
public class P24ServiceImpl implements P24Service {

    private Logger logger = Logger.getLogger(P24ServiceImpl.class);

    @Value("${p24.merchantId}")
    private int merchantId;

    @Value("${p24.posId}")
    private int posId;

    @Value("${p24.crc}")
    private String crc;

    @Value("${p24.url}")
    private String url;

    @Value("${p24.url_return}")
    private String urlReturn;

    @Value("${p24.url_status}")
    private String urlStatus;

    private String apiVersion = "3.2";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Test connection with p24
     */
    @Override
    public void testConnection() {

        String in = posId + "|" + crc;
        String sig = DigestUtils.md5Digest(in);

        logger.debug(in);
        logger.debug(sig);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

        map.add("p24_merchant_id", String.valueOf(merchantId));
        map.add("p24_pos_id", String.valueOf(posId));
        map.add("p24_sign", sig);

        String res = restTemplate.postForObject(url + "/testConnection", map, String.class);

        logger.debug(res);

    }

    @Override
    public Response trnRegister(String sessionId, double amount, String description, String clientEmail) {
        PaymentDetails
                paymentDetails = new PaymentDetails(sessionId, amount, clientEmail, description);
        return trnRegister(paymentDetails);
    }

    @Override
    public Response trnRegister(PaymentDetails paymentDetails) {

        MultiValueMap params = prepareParamMap(paymentDetails);
        String res = restTemplate.postForObject(url + "/trnRegister", params, String.class);
        logger.debug(res);

        return resToResponse(res);
    }

    @Override
    public String preparePaymentURL(Response response) {
        return url + "/trnRequest/" + response.getToken();
    }

    @Override
    public void trnVerify(String sessionId, double amount, int orderId) {
        trnVerify(sessionId, amount, orderId, Currency.PLN);
    }

    @Override
    public void trnVerify(String sessionId, double amount, int orderId, Currency currency) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

        map.add("p24_merchant_id", String.valueOf(merchantId));
        map.add("p24_pos_id", String.valueOf(posId));
        map.add("p24_session_id", sessionId);
        map.add("p24_amount", String.valueOf((new Double(amount * 100)).intValue()));
        map.add("p24_currency", currency.name());
        map.add("p24_order_id", String.valueOf(orderId));

        map.add("p24_sign", sign(sessionId, amount, orderId, currency));

        logger.debug(map);

        String res = restTemplate.postForObject(url + "/trnVerify", map, String.class);

        logger.debug(res);

    }

    protected MultiValueMap prepareParamMap(PaymentDetails paymentDetails) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

        map.add("p24_merchant_id", String.valueOf(merchantId));
        map.add("p24_pos_id", String.valueOf(posId));
        map.add("p24_session_id", paymentDetails.getSessionId());
        map.add("p24_amount", amountAsString(paymentDetails.getAmount()));
        map.add("p24_currency", paymentDetails.getCurrency().name());
        map.add("p24_description", paymentDetails.getDescription());
        map.add("p24_email", paymentDetails.getClientEmail());
        map.add("p24_country", paymentDetails.getCountry().name());
        map.add("p24_url_return", urlReturn);
        map.add("p24_url_status", urlStatus);
        map.add("p24_api_version", apiVersion);
        map.add("p24_encoding", "UTF-8");


        map.add("p24_sign", sign(paymentDetails));

        logger.debug(map);

        return map;

    }

    public String sign(PaymentDetails paymentDetails) {

        String mergedString = merge(paymentDetails.getSessionId(),
                                    String.valueOf(merchantId),
                                    amountAsString(paymentDetails.getAmount()),
                                    paymentDetails.getCurrency(),
                                    crc);

        logger.debug("mergeString: " + mergedString);

        String sign = DigestUtils.md5Digest(
                mergedString);

        logger.debug("sign: " + sign);

        return sign;
    }

    public String sign(String sessionId, double amount, int orderId, Currency currency) {

        String mergedString = merge(sessionId, orderId, amountAsString(amount),
                                    currency.name(), crc);

        logger.debug("mergeString: " + mergedString);

        String sign = DigestUtils.md5Digest(
                mergedString);

        logger.debug("sign: " + sign);

        return sign;
    }

    private String merge(Object... args) {
        return StringUtils.arrayToDelimitedString(args, "|");
    }

    protected String amountAsString(double amount) {
        return String.valueOf(convetAmount(amount));
    }

    protected int convetAmount(double amount) {
        return (new Double(amount * 100)).intValue();
    }

    public Map<String, String> splitQuery(String query)  {

        Map<String, String> queryPairs = new LinkedHashMap<String, String>();

        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            try {
                queryPairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
                               URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                throw new RuntimeException("błąd konwersji", ex);
            }
        }
        return queryPairs;
    }

    protected Response resToResponse(String source) {

        if(source.equals("Error call")) throw new RuntimeException("Error call");

        Response response = new Response();

        Map<String, String> res = splitQuery(source);

        String code = res.get("code");
        if(code != null)
            response.setCode(Integer.valueOf(code));
        response.setErrorMessage(res.get("errorMessage"));
        response.setToken(res.get("token"));

        return response;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlReturn(String urlReturn) {
        this.urlReturn = urlReturn;
    }

    public void setUrlStatus(String urlStatus) {
        this.urlStatus = urlStatus;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
