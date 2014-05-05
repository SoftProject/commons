package pl.com.softproject.utils.payu.util;

import pl.com.softproject.utils.payu.model.Configuration;

/**
 *
 * @author Leszek Bednorz <lbednorz@softproject.com.pl>
 */
public class Sig {

    public static String createSig(Configuration conf, String sessionId, long timeInSec) {
        String sigNotMd5 = "" + conf.getPosId() + sessionId + timeInSec + conf.getKey1();

        return MD5Hasher.compile(sigNotMd5);
    }
}
