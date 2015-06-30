package pl.com.softproject.utils.freshmail.databind.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import pl.com.softproject.utils.freshmail.config.Tracking;

import java.io.IOException;

/**
 * Class TrackingDeserializer
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class TrackingDeserializer extends JsonDeserializer<Tracking> {

    @Override
    public Tracking deserialize(JsonParser jsonParser,
                                DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        return Tracking.valueOf(jsonParser.getValueAsString());
    }
}
