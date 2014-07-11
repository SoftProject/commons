package pl.com.softproject.emailintegration;

import pl.com.softproject.emailintegration.model.VelocityEmail;

/**
 *
 * @author rafalc
 */
public interface VelocityEmailService {

    /**
     * Metoda wysyła emaila w oparciu o dane zawarte w parametrze metody Obiekt
     * zawiera pola między innymi: od kogo email, do kogo, namiar na szablon
     * velocity, mapa z obiektami z których korzysta szablon
     *
     * @param velocityEmail
     * @return
     */
    boolean sendVelocityEmail(VelocityEmail velocityEmail);
}
