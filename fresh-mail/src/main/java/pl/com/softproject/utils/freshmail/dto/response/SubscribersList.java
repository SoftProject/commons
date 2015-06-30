package pl.com.softproject.utils.freshmail.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pl.com.softproject.utils.freshmail.databind.deserializer.DateDeserializer;
import pl.com.softproject.utils.freshmail.databind.serializer.DateSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * Class SubscribersList
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class SubscribersList implements Serializable {

    @JsonProperty("subscriberListHash")
    private String hash;

    private String name;

    private String description;

    @JsonProperty("creation_date")
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializer.class)
    private Date creationDate;

    @JsonProperty("subscribers_number")
    private int subscribersCount;

    public SubscribersList() {
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getSubscribersCount() {
        return subscribersCount;
    }

    public void setSubscribersCount(int subscribersCount) {
        this.subscribersCount = subscribersCount;
    }

    @Override
    public String toString() {
        return "SubscribersList{" +
               "hash='" + hash + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", creationDate=" + creationDate +
               ", subscribersCount=" + subscribersCount +
               '}';
    }
}
