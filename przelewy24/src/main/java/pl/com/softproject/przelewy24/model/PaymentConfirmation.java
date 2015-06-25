package pl.com.softproject.przelewy24.model;
/**
 * Copyright 2015-06-25 the original author or authors.
 */

/**
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public class PaymentConfirmation {

    private int merchantId;
    private int posId;
    private double amount;
    private Currency currency;
    private int orderId;
    private int method;
    private String statement;
    private String sig;
    private String sessionId;

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "PaymentConfirmation{" +
               "merchantId=" + merchantId +
               ", posId=" + posId +
               ", amount=" + amount +
               ", currency=" + currency +
               ", orderId=" + orderId +
               ", method=" + method +
               ", statement='" + statement + '\'' +
               ", sig='" + sig + '\'' +
               '}';
    }
}
