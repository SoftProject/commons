package pl.com.softproject.security;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



/**
 * @author <a href="mailto:gchas@soft-project.pl">Grzegorz Chaś</a>
 *         $Rev: $, $LastChangedBy: $
 *         $LastChangedDate: $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/commons-jasypt-test.xml")
public class PasswordEncoderImplTest {

    private final Logger logger = Logger.getLogger(PasswordEncoderImplTest.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test() {
        
    }
    
    //@Test
    public void testEncodingDecoding() {

        String encoded = passwordEncoder.encode("1qaz@WSXcde3");

        logger.debug("Encoded: " + encoded);

        String decoded = passwordEncoder.decodePassword(encoded);

        logger.debug("Decoded: " + decoded);

        Assert.assertEquals("1qaz@WSXcde3", decoded);

        boolean matches = passwordEncoder.matches(decoded, encoded);

        logger.debug("Matches: " + matches);

        Assert.assertTrue(matches);
    }
}
