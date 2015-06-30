package pl.com.softproject.utils.freshmail.databind.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import pl.com.softproject.utils.freshmail.config.Tracking;

import java.io.IOException;

/**
 * Class TrackingSerializer
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class TrackingSerializer extends JsonSerializer<Tracking> {

    @Override
    public void serialize(Tracking tracking, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {

        jsonGenerator.writeString(String.valueOf(tracking.getStatus()));
    }
}
