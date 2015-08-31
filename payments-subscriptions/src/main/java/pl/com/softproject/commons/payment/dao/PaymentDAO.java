/**
 * Copyright 2015-08-26 the original author or authors.
 */

package pl.com.softproject.commons.payment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import pl.com.softproject.commons.payment.model.Payment;
import pl.com.softproject.commons.payment.model.PaymentStatus;
import pl.com.softproject.commons.payment.model.SubscriptionType;

import java.util.Date;

/**
 * @author Adrian Lapierre <adrian@soft-project.pl>
 */
public interface PaymentDAO extends JpaRepository<Payment, Long> {

    @Query("select p.subscriptionType from Payment p where p.payer.id = ?1 "
           + "and  p.takeEffectStartDate >= ?2 and p.takeEffectEndDate <= ?2 "
           + "and p.status = pl.com.softproject.commons.payment.model.PaymentStatus.MADE")
    public SubscriptionType findCurrentSubsctiption(Long userDataId, Date date);

    @Modifying
    @Query("update Payment p set p.status = ?1 where p.id = ?2")
    public void updateStatus(PaymentStatus paymentStatus, long paymentId);

    @Query("select p.takeEffectEndDate from Payment p where p.payer.id = ?1 "
           + "and  p.takeEffectStartDate >= ?2 and p.takeEffectEndDate <= ?2 "
           + "and p.status = pl.com.softproject.commons.payment.model.PaymentStatus.MADE")
    public Date findCurrentSubsctiptionExpired(Long userDataId, Date date);
}
