package pl.com.softproject.przelewy24.controller;

import pl.com.softproject.przelewy24.model.PaymentConfirmation;

/**
 * Copyright 2015-06-25 the original author or authors.
 */
public interface ConfirmationCallback {

    boolean handlePayment(PaymentConfirmation paymentConfirmation);

}
