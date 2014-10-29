/*
 * Copyright 2014-10-29 the original author or authors.
 */
package pl.com.softproject.utils.commons.ldap;

import java.io.IOException;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.StartTlsRequest;
import javax.naming.ldap.StartTlsResponse;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public class LdapAuthenticator {

    private static Logger logger = Logger.getLogger(LdapAuthenticator.class);

    private boolean ssl;
    private String urlActiveDirectory;
    private String userBase;

    public static final String SEARCH_BY_SAM_ACCOUNT_NAME = "(sAMAccountName=%s)";
    public static final String SEARCH_GROUP_BY_GROUP_CN = "(&(objectCategory=group)(cn={0}))";
    private static final String MEMBER_OF = "memberOf";
    private static final String[] attrIdsToSearch = new String[]{MEMBER_OF};

    protected LdapContext connect(String login, char[] password) throws NamingException, IOException {

        Hashtable<String, String> env = new Hashtable<String, String>(11);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, urlActiveDirectory);
        LdapContext ctx = null;
        StartTlsResponse tls = null;

        try {
            ctx = new InitialLdapContext(env, null);
            if (ssl) {
                tls = (StartTlsResponse) ctx.extendedOperation(new StartTlsRequest());
                tls.setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("Checking: " + hostname + " in");
                        }
                        try {
                            Certificate[] cert = session.getPeerCertificates();
                            if (logger.isDebugEnabled()) {
                                for (int i = 0; i < cert.length; i++) {
                                    logger.debug(cert[i]);
                                }
                            }
                        } catch (SSLPeerUnverifiedException e) {
                            return false;
                        }
                        return true;
                    }
                });
                tls.negotiate();
            }
            ctx.addToEnvironment(Context.SECURITY_AUTHENTICATION, "DIGEST-MD5");
            ctx.addToEnvironment(Context.SECURITY_PRINCIPAL, login);
            ctx.addToEnvironment(Context.SECURITY_CREDENTIALS, password);
            ctx.reconnect(null);
        } finally {
            if (tls != null) {
                try {
                    tls.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
                tls = null;
            }
        }

        return ctx;
    }

    public boolean autenticateInLdap(String login, char[] password) {

        LdapContext ctx = null;

        try {
            ctx = connect(login, password);
        } catch (Exception e) {
            logger.debug("auth failed", e);
            return false;
        } finally {

            if (ctx != null) {
                try {
                    ctx.close();
                } catch (NamingException e) {
                    logger.error(e.getMessage(), e);
                }
                ctx = null;
            }
        }
        return true;
    }

    public boolean autenticateInLdap(String login, char[] password, String groupName) {

        LdapContext ctx = null;

        try {

            ctx = connect(login, password);
            return memberOf(ctx, login, groupName);

        } catch (Exception e) {
            logger.debug("auth failed", e);
            return false;
        } finally {

            if (ctx != null) {
                try {
                    ctx.close();
                } catch (NamingException e) {
                    logger.error(e.getMessage(), e);
                }
                ctx = null;
            }
        }
    }

    public boolean memberOf(LdapContext ctx, String login, String group) throws NamingException {

        Set<String> groups = memberOf(ctx, login);

        for (String tmp : groups) {
            if (parseDN(tmp).equals(group)) {
                return true;
            }
        }
        return false;

    }

    public Set<String> memberOf(LdapContext ctx, String login) throws NamingException {

        String filter = String.format(SEARCH_BY_SAM_ACCOUNT_NAME, login);
        SearchControls constraints = new SearchControls();
        constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
        constraints.setReturningAttributes(attrIdsToSearch);

        NamingEnumeration results = ctx.search(userBase, filter, constraints);

        if (results == null || !results.hasMore()) {
            logger.info("No result found");
            return Collections.EMPTY_SET;
        }

        // Get result for the first entry found
        SearchResult result = (SearchResult) results.next();

        // Get the entry's distinguished name
        NameParser parser = ctx.getNameParser("");
        parser.parse(ctx.getNameInNamespace());
        parser.parse(userBase);

        parser.parse(new CompositeName(result.getName()).get(0));

        // Get the entry's attributes
        Attributes attrs = result.getAttributes();
        Attribute attr = attrs.get(attrIdsToSearch[0]);

        NamingEnumeration e = attr.getAll();

        Set<String> groups = new HashSet<String>();

        while (e.hasMore()) {
            String value = (String) e.next();
            groups.add(value);
        }

        return groups;

    }

    public String parseDN(String source) {

        Pattern pattern = Pattern.compile("CN=([^,]+),");
        Matcher matcher = pattern.matcher(source);

        return matcher.find() ? matcher.group(1) : "";

    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public void setUrlActiveDirectory(String urlActiveDirectory) {
        this.urlActiveDirectory = urlActiveDirectory;
    }

    public void setUserBase(String userBase) {
        this.userBase = userBase;
    }

}
