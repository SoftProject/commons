package pl.com.softproject.utils.freshmail.databind.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import pl.com.softproject.utils.freshmail.config.SubscriberState;

import java.io.IOException;

/**
 * Class SubscriberStateSerializer
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class SubscriberStateSerializer extends JsonSerializer<SubscriberState> {

    @Override
    public void serialize(SubscriberState subscriberState, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {

        jsonGenerator.writeString(String.valueOf(subscriberState.getCode()));
    }
}
