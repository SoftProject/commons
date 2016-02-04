package pl.com.softproject.commons.invoice;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pl.com.softproject.commons.invoice.util.InvoiceUtil;

import java.awt.*;
import java.io.File;
import java.io.Serializable;

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

        InvoiceUtil.transformInvoice("xslt/invoice-to-efaktura.xslt", "xslt/actual-invoice.xml",
                                     efaktura);

        Desktop.getDesktop().open(efaktura);
    }
}
