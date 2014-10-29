/*
 * Copyright 2014-10-29 the original author or authors.
 */
package pl.com.softproject.utils.commons.ldap;

import java.io.IOException;
import java.util.Set;
import javax.naming.NamingException;
import javax.naming.ldap.LdapContext;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public class LdapAuthenticatorTest {

    private String user = "adrian";
    private char[] pass = "".toCharArray();

    /**
     * Test of autenticateInLdap method, of class LdapAuthenticator.
     */
    @Test
    public void testAutenticateInLdap() {

        LdapAuthenticator auth = new LdapAuthenticator();
        auth.setSsl(false);
        auth.setUrlActiveDirectory("ldap://ad.softproject.local/DC=softproject,DC=local?sAMAccountName?sub?(objectClass=*)");
        auth.setUserBase("DC=softproject,DC=local");

        boolean res = auth.autenticateInLdap(user, pass);

        System.out.println(res);

    }

    @Test
    public void memberOfTest() throws NamingException, IOException {

        LdapAuthenticator auth = new LdapAuthenticator();
        auth.setSsl(false);
        auth.setUrlActiveDirectory("ldap://ad.softproject.local");
        auth.setUserBase("DC=softproject,DC=local");

        boolean res = auth.autenticateInLdap(user, pass, "zarzad");

        System.out.println(res);

        
        
        
        
    }

}
