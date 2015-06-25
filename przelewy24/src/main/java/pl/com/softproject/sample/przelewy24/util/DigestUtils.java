package pl.com.softproject.sample.przelewy24.util;
/**
 * Copyright 2015-06-23 the original author or authors.
 */

/**
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public class DigestUtils {

    public static String md5Digest(String source) {
        String res = org.springframework.util.DigestUtils.md5DigestAsHex(source.getBytes());
        return new String(res);
    }

    public static String md5Digest(int source) {
        return md5Digest(Integer.toString(source));
    }

}
