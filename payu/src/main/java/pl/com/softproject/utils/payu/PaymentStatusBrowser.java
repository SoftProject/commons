package pl.com.softproject.utils.payu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.Logger;
import pl.com.softproject.utils.payu.model.Configuration;
import pl.com.softproject.utils.payu.model.ErrorMessage;
import pl.com.softproject.utils.payu.model.PaymentStatus;
import pl.com.softproject.utils.payu.util.Sig;
import pl.com.softproject.utils.payu.util.TimestampInSecond;

public class PaymentStatusBrowser
{
  private static Logger logger = Logger.getLogger(PaymentStatusBrowser.class);
  private String PayUGatewayUrl = "https://www.platnosci.pl/paygw/UTF/Payment/get/txt";
  
  public PaymentStatus getStatus(Configuration conf, String sessionId)
  {
    long timeInSec = TimestampInSecond.getCurrentTimestamp();
    String sig = Sig.createSig(conf, sessionId, timeInSec);
    HttpURLConnection conn = null;
    try
    {
      String params = "pos_id=" + conf.getPosId() + "&session_id=" + sessionId + "&ts=" + timeInSec + "&sig=" + sig;
      URL url = new URL(this.PayUGatewayUrl + "?" + params);
      conn = (HttpURLConnection)url.openConnection();
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      conn.setRequestProperty("Content-Length", "" + Integer.toString(params.getBytes().length));
      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
      }
      return parsePayUOutput(conn);
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
      return PaymentStatus.S0;
    }
    catch (IOException e)
    {
      URL url;
      e.printStackTrace();
      return PaymentStatus.S0;
    }
    finally
    {
      if (conn != null) {
        conn.disconnect();
      }
    }
  }
  
  private PaymentStatus parsePayUOutput(HttpURLConnection conn)
    throws IOException
  {
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    if (logger.isDebugEnabled()) {
      logger.debug("Output from Server .... ");
    }
    PaymentStatus status = null;
    String output;
    while ((output = br.readLine()) != null)
    {
      if (logger.isDebugEnabled()) {
        logger.debug(output);
      }
      if (output != null)
      {
        if (output.startsWith("error_nr: "))
        {
          String[] lineTab = output.split(":");
          String errorCode = lineTab[1];
          status = PaymentStatus.S0;
          status.setError(ErrorMessage.getByCode(errorCode.trim()));
        }
        if (output.startsWith("trans_status: "))
        {
          String[] lineTab = output.split(":");
          String code = lineTab[1];
          status = PaymentStatus.getByCode(code.trim());
        }
      }
    }
    return status;
  }
  
  public static void main(String[] args)
  {
    PaymentStatusBrowser v = new PaymentStatusBrowser();
    
    Configuration conf = new Configuration("12341324", "---", "f3241234", "---");
    System.out.println(v.getStatus(conf, "12asd"));
    System.out.println(v.getStatus(conf, "asdasd"));
    System.out.println(v.getStatus(conf, "f2217062e9211"));
  }
}
