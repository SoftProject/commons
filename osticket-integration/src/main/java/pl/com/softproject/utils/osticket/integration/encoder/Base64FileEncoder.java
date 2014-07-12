package pl.com.softproject.utils.osticket.integration.encoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.tika.Tika;
import pl.com.softproject.utils.osticket.integration.error.FileToBigError;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Base64FileEncoder
 *
 * @author Marcin Jasinski <mkjasinski@gmail.com>
 */
public class Base64FileEncoder implements FileEncoder {

    @Override
    public List<Map<String, String>> encode(List<File> elements) throws IOException {

        if (elements.isEmpty()) {
            return null;
        }
        HashMap<String, String> map = new HashMap<>();
        for (File file : elements) {
            map.put(file.getName(), encodeFileToBase64Binary(file));
        }

        List<Map<String, String>> result = new ArrayList<>();
        result.add(map);

        return result;
    }

    private String encodeFileToBase64Binary(File file) throws IOException {

        byte[] encoded = Base64.encodeBase64(loadFile(file));

        final Tika tika = new Tika();

        return "data:" + tika.detect(file) + ";base64," + new String(encoded);
    }

    private static byte[] loadFile(File file) throws IOException {

        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            throw new FileToBigError("File to big");
        }
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        is.close();

        return bytes;
    }
}
