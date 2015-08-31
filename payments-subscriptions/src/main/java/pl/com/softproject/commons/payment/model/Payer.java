package pl.com.softproject.commons.payment.model;
/**
 * Copyright 2015-08-31 the original author or authors.
 */

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

/**
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
@Entity
@SuppressWarnings({"PersistenceUnitPresent", "LocalCanBeFinal"})
public class Payer extends BaseEntity {

    @Size(max = 255)
    @NotEmpty
    @Email
    @Column
    private String email;

    /**
     * Data wygaśnięcia subskrypcji
     */
    @Column(name = "subscription_expires_date")
    private Date subscriptionExpiresDate;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getSubscriptionExpiresDate() {
        return subscriptionExpiresDate;
    }

    public void setSubscriptionExpiresDate(Date subscriptionExpiresDate) {
        this.subscriptionExpiresDate = subscriptionExpiresDate;
    }

    @Override
    public String toString() {
        return "Payer{" +
               "email='" + email + '\'' +
               ", subscriptionExpiresDate=" + subscriptionExpiresDate +
               '}';
    }
}
