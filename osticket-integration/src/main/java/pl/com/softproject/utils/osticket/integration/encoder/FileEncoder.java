package pl.com.softproject.utils.osticket.integration.encoder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Class EncoderInterface
 *
 * @author Marcin Jasinski <mkjasinski@gmail.com>
 */
public interface FileEncoder {

    public List<Map<String, String>> encode(List<File> elements) throws IOException;
}
