package pl.com.softproject.commons.invoice;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pl.com.softproject.commons.invoice.util.InvoiceUtil;

import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.UUID;

/**
 * Class XsltTest.
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
@RunWith(JUnit4.class)
@Ignore
public class XsltTest implements Serializable {

    @Test
    public void testTransform() throws Exception {

        File efaktura = File.createTempFile("efaktura", ".xml");

        InputStream inputStream =
                getClass().getClassLoader().getResourceAsStream("xslt/actual-invoice.xml");

        File xmlFile = File.createTempFile(UUID.randomUUID().toString(), ".xml");

        FileUtils.copyInputStreamToFile(inputStream, xmlFile);

        InvoiceUtil.transformInvoice("xslt/invoice-to-efaktura.xslt", xmlFile, efaktura);

        Desktop.getDesktop().open(efaktura);
    }
}
