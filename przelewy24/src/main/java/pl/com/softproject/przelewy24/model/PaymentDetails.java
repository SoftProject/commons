package pl.com.softproject.przelewy24.model;
/**
 * Copyright 2015-06-23 the original author or authors.
 */

/**
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public class PaymentDetails {

    private String sessionId;
    private double amount;
    private Currency currency;
    private String description;
    private String clientEmail;
    private Country country;
    private String transferLabel;

    public PaymentDetails() {

    }

    public PaymentDetails(String sessionId, double amount, String clientEmail, String description) {
        this.sessionId = sessionId;
        this.amount = amount;
        this.clientEmail = clientEmail;
        this.description = description;
        this.country = Country.PL;
        this.currency = Currency.PLN;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getTransferLabel() {
        return transferLabel;
    }

    public void setTransferLabel(String transferLabel) {
        this.transferLabel = transferLabel;
    }
}
