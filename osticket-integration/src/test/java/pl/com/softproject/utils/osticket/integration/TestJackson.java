/*
 * Copyright 2011-08-31 the original author or authors.
 */

package pl.com.softproject.utils.osticket.integration;

import java.io.IOException;
import org.apache.log4j.Logger;


import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pl.com.softproject.utils.osticket.integration.model.Ticket;

/**
 *
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public class TestJackson {
    
    Logger logger = Logger.getLogger(getClass());
    
    @Test
    public void testJson() {
        
        /**
         * @See http://www.mkyong.com/java/how-to-convert-java-object-to-from-json-jackson/
         */
        
        try {
            Ticket ticket = new Ticket("name", "mail", "subject", "message");
            
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(ticket);
            
            System.out.println(json);
            
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
        
    }
    
}
