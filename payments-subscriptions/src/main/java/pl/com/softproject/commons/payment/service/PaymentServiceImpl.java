/**
 * Copyright 2015-08-26 the original author or authors.
 */

package pl.com.softproject.commons.payment.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.com.softproject.commons.payment.dao.PayerDAO;
import pl.com.softproject.commons.payment.dao.PaymentDAO;
import pl.com.softproject.commons.payment.dao.SubscriptionTypeDAO;
import pl.com.softproject.commons.payment.model.Payer;
import pl.com.softproject.commons.payment.model.Payment;
import pl.com.softproject.commons.payment.model.PaymentStatus;
import pl.com.softproject.commons.payment.model.SubscriptionType;
import pl.com.softproject.przelewy24.model.Response;
import pl.com.softproject.przelewy24.service.P24Service;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDAO paymentDAO;

    @Autowired
    private PayerDAO payerDAO;

    @Autowired
    private SubscriptionTypeDAO subscriptionTypeDAO;

    @Autowired
    private P24Service p24Service;

    @Override
    public boolean hasPermiumAccess(@NotNull Payer payer) {
        return hasPermiumAccess(payer.getId());
    }

    @Override
    public boolean hasPermiumAccess(long payerId) {
        SubscriptionType res = paymentDAO.findCurrentSubsctiption(payerId, new Date());
        return res != null ? res.getId() == 1 : false;
    }

    /**
     * Metoda przygotowuje płatność w bazie danych i rejestruje ją po stronie przelewy24
     *
     * @param subscriptionTypeId - abonament
     * @param payer - płatnik
     * @param numberOfSubscriptions - ile okresów aboanamentowych
     * @param transactionTitle - tytuł płatności
     * @return URL do przekierowania użytkownika na stronę płatności
     */
    @Override
    public String buy(long subscriptionTypeId, Payer payer, int numberOfSubscriptions,
                      String transactionTitle) {

        Payment payment = preparePayment(subscriptionTypeId,payer,numberOfSubscriptions);

        Response response = p24Service
                .trnRegister(String.valueOf(payment.getId()), payment.getAmount(),
                             transactionTitle, payment.getPayer().getEmail());

        String url = p24Service.preparePaymentURL(response);

        return url;
    }

    @Override
    public Payment preparePayment(long subscriptionTypeId, Payer payer,
                                  int numberOfSubscriptions) {
        SubscriptionType subscriptionType = subscriptionTypeDAO.findOne(subscriptionTypeId);
        return preparePayment(subscriptionType, payer, numberOfSubscriptions);
    }

    @Override
    public Payment preparePayment(SubscriptionType subscriptionType, Payer payer,
                                  int numberOfSubscriptions) {

        Payment payment = new Payment();
        payment.setAmount(subscriptionType.getPrice() * numberOfSubscriptions);
        payment.setStartDate(new Date());
        payment.setSubscriptionType(subscriptionType);
        payment.setStatus(PaymentStatus.NEW);
        payment.setTimes(numberOfSubscriptions);
        payment.setPayer(payer);

        return paymentDAO.save(payment);

    }

    @Override
    public SubscriptionType findSubscriptionTypeById(long id) {
        return subscriptionTypeDAO.findOne(id);
    }

    @Override
    public Payment makePayment(long paymentId, double amount, Date date, String transactionId) {

        Payment payment = getElement(paymentId);

        payment.setCharge(amount);
        payment.setStatus(PaymentStatus.MADE);
        payment.setEndDate(date);
        payment.setTransactionID(transactionId);

        payment.setTakeEffectStartDate(date);

        DateTime endDate = new DateTime(date);
        endDate = endDate.plusDays(payment.getSubscriptionType().getDaysPeriod() * payment.getTimes());

        payment.setTakeEffectEndDate(endDate.toDate());

        update(payment);
        //updateSubscriptionExpiresDate(payment.getPayer());
        Payer payer = payment.getPayer();
        payer.setSubscriptionExpiresDate(endDate.toDate());
        updatePayer(payer);

        return payment;
    }

    protected void updatePayer(Payer payer) {
        payerDAO.save(payer);
    }

    @Override
    public void updateSubscriptionExpiresDate(Payer payer) {

        Date expired = findSubscriptionExpiresDate(payer);
        payer.setSubscriptionExpiresDate(expired);

        updatePayer(payer);

    }

    @Override
    public Date findSubscriptionExpiresDate(Payer userData) {
        return paymentDAO.findCurrentSubsctiptionExpired(userData.getId(), new Date());
    }

    @Override
    public List<SubscriptionType> findAllSubscriptionTypes() {
        return subscriptionTypeDAO.findByEnabledTrue();
    }

    @Override
    public Payment getElement(Long id) {
        return paymentDAO.findOne(id);
    }

    @Override
    public List<Payment> getElements() {
        return paymentDAO.findAll();
    }

    @Override
    public Payment add(Payment element) {
        return paymentDAO.save(element);
    }

    @Override
    public Payment update(Payment element) {
        return paymentDAO.save(element);
    }

    @Override
    public void delete(Payment element) {
        paymentDAO.delete(element);
    }

    @Override
    public Payer createPayer(Payer payer) {
        return payerDAO.save(payer);
    }
}
