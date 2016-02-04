package pl.com.softproject.commons.invoice.util;

import pl.com.softproject.utils.xml.XSLTTransformator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Class InvoiceUtil.
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public final class InvoiceUtil {

    private InvoiceUtil() {
    }

    public static void transformInvoice(final String xsltTemplate,
                                        final String xmlInvoiceToTransform,
                                        final File transformedXml)
            throws IOException, TransformerException {

        InputStream resourceAsStream =
                InvoiceUtil.class.getClassLoader().getResourceAsStream(xsltTemplate);

        XSLTTransformator xsltTransformator = new XSLTTransformator(resourceAsStream);

        InputStream invoice =
                InvoiceUtil.class.getClassLoader().getResourceAsStream(xmlInvoiceToTransform);

        final FileOutputStream fileOutputStream = new FileOutputStream(transformedXml);

        xsltTransformator.transform(new StreamSource(invoice), new StreamResult(fileOutputStream));
    }
}
