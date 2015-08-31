/*
 * Copyright 2015-08-18 the original author or authors.
 */
package pl.com.softproject.commons.payment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Adrian Lapierre <adrian@soft-project.pl>
 */
@Entity
@Table(name = "payment", schema = "public")
@SuppressWarnings("PersistenceUnitPresent")
public class Payment extends BaseEntity{

    /**
     * start obowiązywania abonamentu - przeważnie jak data otrzymania płatności
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date takeEffectStartDate;

    /**
     * koniec obowiązywania abonamentu - wyliczane po zaksięgowaniu wpłaty
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date takeEffectEndDate;

    /**
     * Ile okresów okresów abonamentowych zamówiono - na tej podstawie wyznaczana jest
     * data końca obowiazywania abonamentu
     */
    private int times;

    /**
     * Data rozpoczęcia procesu płatności
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    /**
     * Data otrzymania potwierdzenia płatności z systemu transakcyjnego
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    /**
     * ID transakcji z systemu płatniczego
     */
    private String transactionID;
    
    /**
     * Należność
     */
    private double charge;
    
    /**
     * Zapłacono
     */
    private double amount;
    

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    /**
     * aktualnie nie używane
     */
    @Column
    private long secureHash;

    /**
     * Zamówiony abonament
     */
    @ManyToOne
    @JoinColumn(name = "subscription_type_id")
    private SubscriptionType subscriptionType;

    /**
     * Płatnik
     */
    @ManyToOne
    @JoinColumn(name = "payer_id")
    private Payer payer;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public long getSecureHash() {
        return secureHash;
    }

    public void setSecureHash(long secureHash) {
        this.secureHash = secureHash;
    }

    public Date getTakeEffectStartDate() {
        return takeEffectStartDate;
    }

    public void setTakeEffectStartDate(Date takeEffectStartDate) {
        this.takeEffectStartDate = takeEffectStartDate;
    }

    public Date getTakeEffectEndDate() {
        return takeEffectEndDate;
    }

    public void setTakeEffectEndDate(Date takeEffectEndDate) {
        this.takeEffectEndDate = takeEffectEndDate;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    @Override
    public String toString() {
        return "Payment{" +
               "takeEffectStartDate=" + takeEffectStartDate +
               ", takeEffectEndDate=" + takeEffectEndDate +
               ", times=" + times +
               ", startDate=" + startDate +
               ", endDate=" + endDate +
               ", transactionID='" + transactionID + '\'' +
               ", charge=" + charge +
               ", amount=" + amount +
               ", status=" + status +
               ", secureHash=" + secureHash +
               ", subscriptionType=" + subscriptionType +
               ", payer=" + payer +
               '}';
    }
}
