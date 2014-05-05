/*  1:   */ package pl.com.softproject.utils.payu.util;
/*  2:   */ 
/*  3:   */ public class TimestampInSecond
/*  4:   */ {
/*  5:   */   public static long getCurrentTimestamp()
/*  6:   */   {
/*  7:14 */     long timeInMills = System.currentTimeMillis();
/*  8:15 */     long timeInSec = timeInMills / 1000L;
/*  9:16 */     return timeInSec;
/* 10:   */   }
/* 11:   */ }


/* Location:           D:\Programowanie\Commons\private-commons\payu\target\classes\
 * Qualified Name:     pl.com.softproject.utils.payu.util.TimestampInSecond
 * JD-Core Version:    0.7.0.1
 */