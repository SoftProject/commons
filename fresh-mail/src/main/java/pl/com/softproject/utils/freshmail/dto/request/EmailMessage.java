package pl.com.softproject.utils.freshmail.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pl.com.softproject.utils.freshmail.config.Encoding;
import pl.com.softproject.utils.freshmail.config.Tracking;
import pl.com.softproject.utils.freshmail.databind.deserializer.EncodingDeserializer;
import pl.com.softproject.utils.freshmail.databind.deserializer.TrackingDeserializer;
import pl.com.softproject.utils.freshmail.databind.serializer.EncodingSerializer;
import pl.com.softproject.utils.freshmail.databind.serializer.TrackingSerializer;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * Class EmailMessage
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class EmailMessage implements Serializable {

    @NotNull
    private String subscriber;

    @NotNull
    private String subject;

    private String html;

    private String text;

    private String from;

    @JsonProperty("from_name")
    private String fromName;

    @JsonProperty("reply_to")
    private String replyTo;

    @JsonSerialize(using = EncodingSerializer.class)
    @JsonDeserialize(using = EncodingDeserializer.class)
    private Encoding encoding = Encoding.UTF8;

    private String attachments;

    @JsonSerialize(using = TrackingSerializer.class)
    @JsonDeserialize(using = TrackingDeserializer.class)
    private Tracking tracking;

    private String domain;

    private String tag;

    public EmailMessage() {
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public Encoding getEncoding() {
        return encoding;
    }

    public void setEncoding(Encoding encoding) {
        this.encoding = encoding;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public Tracking getTracking() {
        return tracking;
    }

    public void setTracking(Tracking tracking) {
        this.tracking = tracking;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "EmailMessage{" +
               "subscriber='" + subscriber + '\'' +
               ", subject='" + subject + '\'' +
               ", html='" + html + '\'' +
               ", text='" + text + '\'' +
               ", from='" + from + '\'' +
               ", fromName='" + fromName + '\'' +
               ", replyTo='" + replyTo + '\'' +
               ", encoding=" + encoding +
               ", attachments='" + attachments + '\'' +
               ", tracking=" + tracking +
               ", domain='" + domain + '\'' +
               ", tag='" + tag + '\'' +
               '}';
    }
}
