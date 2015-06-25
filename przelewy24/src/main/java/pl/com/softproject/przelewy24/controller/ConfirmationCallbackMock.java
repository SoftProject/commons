package pl.com.softproject.przelewy24.controller;
/**
 * Copyright 2015-06-25 the original author or authors.
 */

import pl.com.softproject.przelewy24.model.PaymentConfirmation;

/**
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public class ConfirmationCallbackMock implements ConfirmationCallback {

    @Override
    public boolean handlePayment(PaymentConfirmation paymentConfirmation) {
        return true;
    }
}
