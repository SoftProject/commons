package pl.com.softproject.utils.freshmail.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pl.com.softproject.utils.freshmail.config.SubscriberConfirm;
import pl.com.softproject.utils.freshmail.config.SubscriberState;
import pl.com.softproject.utils.freshmail.databind.deserializer.SubscriberConfirmDeserializer;
import pl.com.softproject.utils.freshmail.databind.deserializer.SubscriberStateDeserializer;
import pl.com.softproject.utils.freshmail.databind.serializer.SubscriberConfirmSerializer;
import pl.com.softproject.utils.freshmail.databind.serializer.SubscriberStateSerializer;
import pl.com.softproject.utils.freshmail.dto.DictionaryField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * Class Subscriber
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class Subscriber implements Serializable {

    @NotNull
    private String email;

    @NotNull
    private String list;

    @JsonSerialize(using = SubscriberStateSerializer.class)
    @JsonDeserialize(using = SubscriberStateDeserializer.class)
    private SubscriberState state = SubscriberState.TO_ACTIVATE;

    @JsonSerialize(using = SubscriberConfirmSerializer.class)
    @JsonDeserialize(using = SubscriberConfirmDeserializer.class)
    private SubscriberConfirm confirm = SubscriberConfirm.CONFIRM;

    @JsonProperty("custom_fields")
    private List<DictionaryField> customFields = new ArrayList<>();

    public Subscriber() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public SubscriberState getState() {
        return state;
    }

    public void setState(SubscriberState state) {
        this.state = state;
    }

    public SubscriberConfirm getConfirm() {
        return confirm;
    }

    public void setConfirm(SubscriberConfirm confirm) {
        this.confirm = confirm;
    }

    public List<DictionaryField> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<DictionaryField> customFields) {
        this.customFields = customFields;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
               "email='" + email + '\'' +
               ", list='" + list + '\'' +
               ", state=" + state +
               ", confirm=" + confirm +
               ", customFields=" + customFields +
               '}';
    }
}
