package pl.com.softproject.commons.payment.service;
/**
 * Copyright 2015-08-31 the original author or authors.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.com.softproject.commons.payment.model.Payer;
import pl.com.softproject.commons.payment.model.Payment;

import java.util.Date;

/**
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context-db.xml")
public class PaymentTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    public void test() {
        System.out.println("!!");

        Payer payer = new Payer();
        payer.setEmail("adrian@soft-project.pl");

        paymentService.createPayer(payer);

        Payment payment = paymentService.preparePayment(1L, payer, 1);

        System.out.println(payment);

        payment = paymentService.makePayment(payment.getId(), payment.getAmount(), new Date(), payment.getTransactionID());

        System.out.println(payment);

    }

}
