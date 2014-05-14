/*
 * Copyright 2014-05-14 the original author or authors.
 */

package pl.com.softproject.commons.invoice;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

/**
 *
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public class PrintServiceImplTest {
    
   
    /**
     * Test of exportPDF method, of class PrintServiceImpl.
     */
    @Test
    public void testExportPDF() throws Exception {
        
        PrintServiceImpl printServiceImpl = new PrintServiceImpl();
        printServiceImpl.setResource(new ClassPathResource("invoice-i18n.jasper"));
        
        File file = File.createTempFile("invoice", ".pdf");
        printServiceImpl.exportPDFi18n(new File("src/test/resources/FS 1_MAG_05_2012.xml"), file.getAbsolutePath(), "pl_PL");
        
        Desktop.getDesktop().open(file);
        
        System.out.println(file);
    }
    
    //@Test
    public void testPrint() throws Exception {
        
        PrintServiceImpl printServiceImpl = new PrintServiceImpl();
        printServiceImpl.setResource(new ClassPathResource("invoice-i18n.jasper"));
        
        printServiceImpl.printByDefaultPrinter(new File("src/test/resources/FS 1_MAG_05_2012.xml"), 1, "pl_PL", true);
        
    }
    
}
