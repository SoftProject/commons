package pl.com.softproject.commons.payment.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Copyright 2015-08-26 the original author or authors.
 */
@Entity
@Table(name = "subscription_type", schema = "public")
public class SubscriptionType extends BaseEntity {

    @Size(max = 128)
    private String name;
    @Size(max = 512)
    private String description;
    private double price;
    private boolean enabled = true;
    private int daysPeriod;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getDaysPeriod() {
        return daysPeriod;
    }

    public void setDaysPeriod(int daysPeriod) {
        this.daysPeriod = daysPeriod;
    }

    @Override
    public String toString() {
        return "SubscriptionType{" +
               "name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", price=" + price +
               ", enabled=" + enabled +
               ", daysPeriod=" + daysPeriod +
               '}';
    }
}
