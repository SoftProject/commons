package pl.com.softproject.emailintegration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.com.softproject.emailintegration.model.TestObject;
import pl.com.softproject.emailintegration.model.VelocityEmail;

/**
 *
 * @author rafalc
 */
public class VelocityTest {

    private static ApplicationContext ctx;

    public static void main(String[] args) {
        System.out.println("start");
        ctx = new ClassPathXmlApplicationContext("test-context.xml");

        VelocityEmailService emailService = (VelocityEmailService) ctx.getBean("velocityEmailService");

        VelocityEmail emailContent = new VelocityEmail();
        emailContent.setFromEmailAddress(".......@go2.pl");
        emailContent.setToEmailAddress(".....@soft-project.pl");
        emailContent.setSubject("Pozdrowienia z Velocity template");
        emailContent.setVelocityTemplateResourcePath("pl/com/softproject/emailintegration/sample.vm");
        
        TestObject to = new TestObject("janek@soft-project.pl", "Janek");
        emailContent.getModel().put("user", to);
                
        emailService.sendVelocityEmail(emailContent);
        System.out.println("stop");
    }
}
