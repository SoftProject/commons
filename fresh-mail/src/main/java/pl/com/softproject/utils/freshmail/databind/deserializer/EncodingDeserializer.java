package pl.com.softproject.utils.freshmail.databind.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import pl.com.softproject.utils.freshmail.config.Encoding;

import java.io.IOException;

/**
 * Class EncodingDeserializer
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class EncodingDeserializer extends JsonDeserializer<Encoding> {

    @Override
    public Encoding deserialize(JsonParser jsonParser,
                                DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        return Encoding.valueOf(jsonParser.getValueAsString());
    }
}
