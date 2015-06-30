package pl.com.softproject.utils.freshmail.databind.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import pl.com.softproject.utils.freshmail.config.SubscriberConfirm;

import java.io.IOException;

/**
 * Class SubscriberConfirmSerializer
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class SubscriberConfirmSerializer extends JsonSerializer<SubscriberConfirm> {

    @Override
    public void serialize(SubscriberConfirm subscriberConfirm, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {

        jsonGenerator.writeString(String.valueOf(subscriberConfirm.getCode()));
    }
}
