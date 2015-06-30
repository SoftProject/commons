package pl.com.softproject.utils.freshmail.databind.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import pl.com.softproject.utils.freshmail.config.Encoding;

import java.io.IOException;

/**
 * Class EncodingSerializer
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class EncodingSerializer extends JsonSerializer<Encoding> {

    @Override
    public void serialize(Encoding encoding, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {

        jsonGenerator.writeString(String.valueOf(encoding.getEncoding()));
    }
}
