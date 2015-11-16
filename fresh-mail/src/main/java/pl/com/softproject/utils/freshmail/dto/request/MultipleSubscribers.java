package pl.com.softproject.utils.freshmail.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pl.com.softproject.utils.freshmail.config.SubscriberConfirm;
import pl.com.softproject.utils.freshmail.config.SubscriberState;
import pl.com.softproject.utils.freshmail.databind.deserializer.SubscriberConfirmDeserializer;
import pl.com.softproject.utils.freshmail.databind.deserializer.SubscriberStateDeserializer;
import pl.com.softproject.utils.freshmail.databind.serializer.SubscriberConfirmSerializer;
import pl.com.softproject.utils.freshmail.databind.serializer.SubscriberStateSerializer;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

/**
 * Class MultipleSubscribers.
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class MultipleSubscribers implements Serializable {

    @NotNull
    private Set<MultipleSubscriber> subscribers = new LinkedHashSet<>();

    @NotNull
    private String list;

    @JsonSerialize(using = SubscriberConfirmSerializer.class)
    @JsonDeserialize(using = SubscriberConfirmDeserializer.class)
    private SubscriberConfirm confirm = SubscriberConfirm.CONFIRM;

    @JsonSerialize(using = SubscriberStateSerializer.class)
    @JsonDeserialize(using = SubscriberStateDeserializer.class)
    private SubscriberState state = SubscriberState.TO_ACTIVATE;

    public MultipleSubscribers() {
    }

    public MultipleSubscribers(final Set<MultipleSubscriber> subscribers, final String list) {
        this(subscribers, list, SubscriberConfirm.CONFIRM, SubscriberState.TO_ACTIVATE);
    }

    public MultipleSubscribers(final Set<MultipleSubscriber> subscribers, final String list,
                               final SubscriberConfirm confirm, final SubscriberState state) {
        this.subscribers = subscribers;
        this.list = list;
        this.confirm = confirm;
        this.state = state;
    }

    public SubscriberState getState() {
        return state;
    }

    public Set<MultipleSubscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(final Set<MultipleSubscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public String getList() {
        return list;
    }

    public void setList(final String list) {
        this.list = list;
    }

    public SubscriberConfirm getConfirm() {
        return confirm;
    }

    public void setConfirm(final SubscriberConfirm confirm) {
        this.confirm = confirm;
    }
}
