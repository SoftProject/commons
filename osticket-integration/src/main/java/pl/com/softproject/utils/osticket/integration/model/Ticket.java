/*
 * Copyright 2011-08-31 the original author or authors.
 */

package pl.com.softproject.utils.osticket.integration.model;

/**
 *
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public class Ticket {
    
    private String source;
    private String name;
    private String email;
    private String subject;
    private String message;
    private boolean alert;
    private boolean autorespond; 

    public Ticket() {
    }

    public Ticket(String name, String email, String subject, String message) {
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public Ticket(String name, String email, String subject, String message, String source) {
        this.source = source;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    public boolean isAutorespond() {
        return autorespond;
    }

    public void setAutorespond(boolean autorespond) {
        this.autorespond = autorespond;
    }
    
    
    
}
