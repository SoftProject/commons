package pl.com.softproject.commons.invoice;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

/**
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
@RunWith(JUnit4.class)
@Ignore
public class PrintServiceImplTest {

    @Test
    public void testExportPDF() throws Exception {

        PrintServiceImpl printServiceImpl = new PrintServiceImpl();
        printServiceImpl.setResource(new ClassPathResource("invoice-i18n.jasper"));

        File file = File.createTempFile("invoice", ".pdf");
        printServiceImpl.exportPDFi18n(new File("src/test/resources/FS 1_MAG_05_2012.xml"),
                                       file.getAbsolutePath(), "pl_PL");
    }

    @Test
    public void testPrint() throws Exception {

        PrintServiceImpl printServiceImpl = new PrintServiceImpl();
        printServiceImpl.setResource(new ClassPathResource("invoice-i18n.jasper"));

        printServiceImpl
                .printByDefaultPrinter(new File("src/test/resources/FS 1_MAG_05_2012.xml"), 1,
                                       "pl_PL", true);
    }
}
