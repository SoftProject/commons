/**
 * Copyright 2015-08-31 the original author or authors.
 */
package pl.com.softproject.commons.payment.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.softproject.commons.payment.model.Payer;



/**
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public interface PayerDAO extends JpaRepository<Payer, Long> {

}
