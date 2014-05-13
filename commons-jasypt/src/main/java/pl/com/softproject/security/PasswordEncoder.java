package pl.com.softproject.security;

/**
 * @author <a href="mailto:gchas@soft-project.pl">Grzegorz Chaś</a>
 *         $Rev: $, $LastChangedBy: $
 *         $LastChangedDate: $
 */
public interface PasswordEncoder extends org.springframework.security.crypto.password.PasswordEncoder {

    String decodePassword(CharSequence encodedPassword);

    void setPasswordForEncryptor(String password);
}
