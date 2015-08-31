/**
 * Copyright 2015-08-26 the original author or authors.
 */

package pl.com.softproject.commons.payment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.softproject.commons.payment.model.SubscriptionType;

import java.util.List;

/**
 * @author Adrian Lapierre <adrian@soft-project.pl>
 */
public interface SubscriptionTypeDAO extends JpaRepository<SubscriptionType, Long> {

    public List<SubscriptionType> findByEnabledTrue();
}
