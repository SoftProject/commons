/*  1:   */ package pl.com.softproject.utils.payu.util;
/*  2:   */ 
/*  3:   */ import pl.com.softproject.utils.payu.model.Configuration;
/*  4:   */ 
/*  5:   */ public class Sig
/*  6:   */ {
/*  7:   */   public static String createSig(Configuration conf, String sessionId, long timeInSec)
/*  8:   */   {
/*  9:18 */     String sigNotMd5 = "" + conf.getPosId() + sessionId + timeInSec + conf.getKey1();
/* 10:   */     
/* 11:20 */     return MD5Hasher.compile(sigNotMd5);
/* 12:   */   }
/* 13:   */ }


/* Location:           D:\Programowanie\Commons\private-commons\payu\target\classes\
 * Qualified Name:     pl.com.softproject.utils.payu.util.Sig
 * JD-Core Version:    0.7.0.1
 */