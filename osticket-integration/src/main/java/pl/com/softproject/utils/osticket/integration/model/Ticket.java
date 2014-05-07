/**
 * This file is part of osTicketIntegration.
 *
 * (c) 2014 SoftProject
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package pl.com.softproject.utils.osticket.integration.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class Ticket
 *
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 * @author Marcin Jasinski <mkjasinski@gmail.com>
 */
public class Ticket {

    private String name;
    private String email;
    private String source = "API";
    private String subject;
    private String message;
    private boolean alert;
    @JsonProperty("autorespond")
    private boolean autoRespond;
    private List<Map<String, String>> attachments = new ArrayList<>();
    private int topicId = 1;

    public Ticket() {
    }

    public Ticket(String name, String email, String subject, String message, boolean alert, boolean autoRespond) {
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.alert = alert;
        this.autoRespond = autoRespond;
    }

    public String getName() {
        return name;
    }

    public Ticket setName(String name) {
        this.name = name;

        return this;
    }

    public String getEmail() {
        return email;
    }

    public Ticket setEmail(String email) {
        this.email = email;

        return this;
    }

    public String getSource() {
        return source;
    }

    public String getSubject() {
        return subject;
    }

    public Ticket setSubject(String subject) {
        this.subject = subject;

        return this;
    }

    public String getMessage() {
        return message;
    }

    public Ticket setMessage(String message) {
        this.message = message;

        return this;
    }

    public boolean isAlert() {
        return alert;
    }

    public Ticket setAlert(boolean alert) {
        this.alert = alert;

        return this;
    }

    public boolean isAutoRespond() {
        return autoRespond;
    }

    public Ticket setAutoRespond(boolean autoRespond) {
        this.autoRespond = autoRespond;

        return this;
    }

    public List<Map<String, String>> getAttachments() {
        return attachments;
    }

    public Ticket setAttachments(List<Map<String, String>> attachments) {
        this.attachments = attachments;

        return this;
    }

    public int getTopicId() {
        return topicId;
    }

    public Ticket setTopicId(int topicId) {
        this.topicId = topicId;

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (alert != ticket.alert) return false;
        if (autoRespond != ticket.autoRespond) return false;
        if (topicId != ticket.topicId) return false;
        if (attachments != null ? !attachments.equals(ticket.attachments) : ticket.attachments != null) return false;
        if (email != null ? !email.equals(ticket.email) : ticket.email != null) return false;
        if (message != null ? !message.equals(ticket.message) : ticket.message != null) return false;
        if (name != null ? !name.equals(ticket.name) : ticket.name != null) return false;
        if (source != null ? !source.equals(ticket.source) : ticket.source != null) return false;
        if (subject != null ? !subject.equals(ticket.subject) : ticket.subject != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (alert ? 1 : 0);
        result = 31 * result + (autoRespond ? 1 : 0);
        result = 31 * result + (attachments != null ? attachments.hashCode() : 0);
        result = 31 * result + topicId;
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", source='" + source + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", alert=" + alert +
                ", autoRespond=" + autoRespond +
                ", attachments=" + attachments +
                ", topicId=" + topicId +
                '}';
    }
}
