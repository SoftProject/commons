package pl.com.softproject.security;

import org.jasypt.util.text.StrongTextEncryptor;
import pl.com.softproject.exception.SecurityPasswordNotSetException;

/**
 * @author <a href="mailto:gchas@soft-project.pl">Grzegorz Chaś</a>
 *         $Rev: $, $LastChangedBy: $
 *         $LastChangedDate: $
 */
public class PasswordEncoderImpl implements PasswordEncoder {

    private String password;
    private StrongTextEncryptor textEncryptor = new StrongTextEncryptor();

    @Override
    public String encode(CharSequence rawPassword) {
        checkInitialization();
        return textEncryptor.encrypt(rawPassword.toString());
    }

    @Override
    public String decodePassword(CharSequence encodedPassword) {
        checkInitialization();
        return textEncryptor.decrypt(encodedPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        checkInitialization();

        String currentRawPass = textEncryptor.decrypt(encodedPassword);
        if (currentRawPass.equals(rawPassword.toString()))
            return true;

        return false;
    }

    private synchronized void checkInitialization() {
        if (password == null || password.isEmpty())
             throw new SecurityPasswordNotSetException("Security password must be set.");
    }

    public void setPasswordForEncryptor(String password) {
        this.password = password;
        this.textEncryptor.setPassword(password);
    }
}