/*
 * Copyright 2011-08-31 the original author or authors.
 */
package pl.com.softproject.commons.payment.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.com.softproject.commons.payment.service.PaymentService;
import pl.com.softproject.przelewy24.controller.ConfirmationCallback;
import pl.com.softproject.przelewy24.model.PaymentConfirmation;
import pl.com.softproject.przelewy24.service.P24Service;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
@Controller
public class PaymentConfirmationController {
    
    protected Logger logger = Logger.getLogger(getClass());

    @Autowired
    private P24Service p24Service;

    @Autowired
    private ConfirmationCallback confirmationCallback;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = {"/payment-confirmation/", "/payment-confirmation"}, method = RequestMethod.POST)
    public @ResponseBody String paymentConfirmation(@RequestParam Map<String,String> allRequestParams, ModelMap model) {

        logger.debug("params " + allRequestParams);

        PaymentConfirmation paymentConfirmation = p24Service.fromMap(allRequestParams);

        logger.debug("amount: " + paymentConfirmation.getAmount());

        paymentService.makePayment(Long.valueOf(paymentConfirmation.getSessionId()),
                                                paymentConfirmation.getAmount(),
                                                new Date(),
                                                String.valueOf(paymentConfirmation.getOrderId()));

        if(confirmationCallback.handlePayment(paymentConfirmation)) {
            p24Service.trnVerify(allRequestParams.get("p24_session_id"), paymentConfirmation.getAmount(),
                                 Integer.valueOf(allRequestParams.get("p24_order_id")));
        }

        return allRequestParams.toString();
    }

}
