/**
 * Copyright 2015-08-26 the original author or authors.
 */

package pl.com.softproject.commons.payment.service;

import pl.com.softproject.commons.payment.model.Payer;
import pl.com.softproject.commons.payment.model.Payment;
import pl.com.softproject.commons.payment.model.SubscriptionType;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * @author Adrian Lapierre <adrian@soft-project.pl>
 */
public interface PaymentService {

    boolean hasPermiumAccess(@NotNull Payer payer);

    boolean hasPermiumAccess(long userDataId);


    String buy(long subscriptionTypeId, Payer payer, int numberOfSubscriptions,
               String transactionTitle);

    Payment preparePayment(long subscriptionTypeId, Payer payer, int numberOfSubscriptions);

    Payment preparePayment(SubscriptionType subscriptionType, Payer payer,
                           int numberOfSubscriptions);

    SubscriptionType findSubscriptionTypeById(long id);

    Payment makePayment(long paymentId, double amount, Date date, String transactionId);

    void updateSubscriptionExpiresDate(Payer payer);

    Date findSubscriptionExpiresDate(Payer payer);

    List<SubscriptionType> findAllSubscriptionTypes();

    public Payment getElement(Long id);

    public List<Payment> getElements();

    public Payment add(Payment element);

    public Payment update(Payment element);

    public void delete(Payment element);

    Payer createPayer(Payer payer);
}
