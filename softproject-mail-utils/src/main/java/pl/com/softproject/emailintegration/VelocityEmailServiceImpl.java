package pl.com.softproject.emailintegration;

import org.apache.velocity.app.VelocityEngine;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;
import pl.com.softproject.emailintegration.model.VelocityEmail;

/**
 *
 * @author rafalc
 */
public class VelocityEmailServiceImpl implements VelocityEmailService {

    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;

    public boolean sendVelocityEmail(final VelocityEmail velocityEmail) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(velocityEmail.getToEmailAddress());
                message.setFrom(velocityEmail.getFromEmailAddress());
                message.setSubject(velocityEmail.getSubject());

                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, velocityEmail.getVelocityTemplateResourcePath(), velocityEmail.getModel());
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);

        return true;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }
}
