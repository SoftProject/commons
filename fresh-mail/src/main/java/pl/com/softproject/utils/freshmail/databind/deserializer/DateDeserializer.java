package pl.com.softproject.utils.freshmail.databind.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class DateDeserializer
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class DateDeserializer extends JsonDeserializer<Date> {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        try {
            return formatter.parse(jsonParser.getValueAsString());
        } catch (ParseException e) {
            return null;
        }
    }
}
