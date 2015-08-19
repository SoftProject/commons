package pl.com.softproject.przelewy24.model;
/**
 * Copyright 2015-06-25 the original author or authors.
 */

/**
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public class PaymentConfirmation {

    /**
     * ID Sprzedawcy 
     */
    private int merchantId;
    
    /**
     * ID Sklepu (domyślnie ID Sprzedawcy) 
     */
    private int posId;
    
    /**
     * Kwota transakcji - zostanie przeliczona do odpowiedniego formatu 
     */
    private double amount;
    
    /**
     * Waluta
     */
    private Currency currency;
    
    /**
     * Numer transakcji nadany przez Przelewy24 
     */
    private int orderId;
    
    /**
     * Metoda płatności użyta przez klienta 
     */
    private int method;
    
    /**
     * Tytuł przelewu
     */
    private String statement;
    
    /**
     * Suma kontrolna wyliczana wg z pól:
     * p24_session_id, p24_order_id,
     * p24_amount ,p24_currency i pola
     * "Klucz CRC" 
     */
    private String sig;
    
    /**
     * Unikalny identyfikator z systemu sprzedawcy 
     */
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
