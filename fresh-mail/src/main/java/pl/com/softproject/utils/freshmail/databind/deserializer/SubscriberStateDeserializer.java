package pl.com.softproject.utils.freshmail.databind.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import pl.com.softproject.utils.freshmail.config.SubscriberState;

import java.io.IOException;

/**
 * Class SubscriberStateDeserializer
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class SubscriberStateDeserializer extends JsonDeserializer<SubscriberState> {

    @Override
    public SubscriberState deserialize(JsonParser jsonParser,
                                       DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        return SubscriberState.valueOf(jsonParser.getValueAsString());
    }
}
