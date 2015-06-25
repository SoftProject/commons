package pl.com.softproject.przelewy24.service;

import pl.com.softproject.przelewy24.model.Currency;
import pl.com.softproject.przelewy24.model.PaymentConfirmation;
import pl.com.softproject.przelewy24.model.PaymentDetails;
import pl.com.softproject.przelewy24.model.Response;

import java.util.Map;

/**
 * Copyright 2015-06-23 the original author or authors.
 */
public interface P24Service {

    void testConnection();

    Response trnRegister(String sessionId, double amount, String description, String clientEmail);

    Response trnRegister(PaymentDetails paymentDetails);

    String preparePaymentURL(Response response);

    void trnVerify(String sessionId, double amount, int orderId);

    void trnVerify(String sessionId, double amount, int orderId, Currency currency);

    PaymentConfirmation fromMap(Map<String, String> map);
}
