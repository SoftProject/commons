package pl.com.softproject.utils.payu.util;

/**
 *
 * @author Leszek Bednorz <lbednorz@softproject.com.pl>
 */
public class TimestampInSecond {

    public static long getCurrentTimestamp() {
        long timeInMills = System.currentTimeMillis();
        long timeInSec = timeInMills / 1000L;
        return timeInSec;
    }
}
