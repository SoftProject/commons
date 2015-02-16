/*
 * Copyright 2014-10-29 the original author or authors.
 */
package pl.com.softproject.utils.commons.ldap;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import javax.naming.NamingException;

/**
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public class LdapAuthenticatorTest {

    private String user = "adrian";
    private char[] pass = "".toCharArray();

    /**
     * Test of autenticateInLdap method, of class LdapAuthenticator.
     */
    @Test
    @Ignore("no password for user")
    public void testAutenticateInLdap() {

        LdapAuthenticator auth = new LdapAuthenticator();
        auth.setSsl(false);
        auth.setUrlActiveDirectory(
                "ldap://ad.softproject.local/DC=softproject,DC=local?sAMAccountName?sub?(objectClass=*)");
        auth.setUserBase("DC=softproject,DC=local");

        boolean res = auth.autenticateInLdap(user, pass);

        Assert.assertTrue(res);
    }

    @Test
    @Ignore("no password for user")
    public void memberOfTest() throws NamingException, IOException {

        LdapAuthenticator auth = new LdapAuthenticator();
        auth.setSsl(false);
        auth.setUrlActiveDirectory("ldap://ad.softproject.local");
        auth.setUserBase("DC=softproject,DC=local");

        boolean res = auth.autenticateInLdap(user, pass, "zarzad");

        Assert.assertTrue(res);
    }
}
