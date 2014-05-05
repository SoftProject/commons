package pl.com.softproject.utils.payu.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;

public class MD5Hasher
{
  private static Logger logger = Logger.getLogger(MD5Hasher.class);
  
  public static String compile(String value)
  {
    if (value == null) {
      return null;
    }
    MessageDigest md = null;
    try
    {
      md = MessageDigest.getInstance("MD5");
    }
    catch (NoSuchAlgorithmException ex)
    {
      logger.error("Problem z inicjalizacjią mechanizmu MD5", ex);
      return null;
    }
    md.update(value.getBytes());
    byte[] byteData = md.digest();
    
    String hashedValue = buildString(byteData);
    if (logger.isDebugEnabled()) {
      logger.debug("Digest(in hex format):: " + hashedValue);
    }
    return hashedValue;
  }
  
  private static String buildString(byte[] byteData)
  {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < byteData.length; i++) {
      sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
    }
    String hashedValue = sb.toString();
    return hashedValue;
  }
}
