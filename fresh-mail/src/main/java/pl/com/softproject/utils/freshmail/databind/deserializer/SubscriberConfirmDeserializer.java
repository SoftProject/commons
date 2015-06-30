package pl.com.softproject.utils.freshmail.databind.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import pl.com.softproject.utils.freshmail.config.SubscriberConfirm;

import java.io.IOException;

/**
 * Class SubscriberConfirmDeserializer
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class SubscriberConfirmDeserializer extends JsonDeserializer<SubscriberConfirm> {

    @Override
    public SubscriberConfirm deserialize(JsonParser jsonParser,
                                         DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        return SubscriberConfirm.valueOf(jsonParser.getValueAsString());
    }
}
