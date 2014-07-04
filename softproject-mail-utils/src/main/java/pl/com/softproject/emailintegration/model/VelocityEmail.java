package pl.com.softproject.emailintegration.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa przechowuje wszystkie parametry potrzebne do wysłania email-a.
 * Wszystkie parametry są wymagane;
 *
 * @author rafalc
 */
public class VelocityEmail {

    private String fromEmailAddress;
    private String toEmailAddress;
    private String subject;
    private String velocityTemplateResourcePath;
    private Map model;

    public VelocityEmail() {
        model = new HashMap();
    }

    public String getToEmailAddress() {
        return toEmailAddress;
    }

    public void setToEmailAddress(String toEmailAddress) {
        this.toEmailAddress = toEmailAddress;
    }

    public String getVelocityTemplateResourcePath() {
        return velocityTemplateResourcePath;
    }

    public void setVelocityTemplateResourcePath(String velocityTemplateResourcePath) {
        this.velocityTemplateResourcePath = velocityTemplateResourcePath;
    }

    public Map getModel() {
        return model;
    }

    public void setModel(Map model) {
        this.model = model;
    }

    public String getFromEmailAddress() {
        return fromEmailAddress;
    }

    public void setFromEmailAddress(String fromEmailAddress) {
        this.fromEmailAddress = fromEmailAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
