package pl.com.softproject.commons.invoice;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;

import pl.com.softproject.commons.invoice.tools.LangUtil;
import pl.com.softproject.commons.model.invoice.Invoice;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

/**
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public class PrintServiceImpl {

    private static Logger logger = Logger.getLogger(PrintServiceImpl.class);

    private Resource resource;
    private LangUtil langUtil = new LangUtil();
    private InvoiceModelSerializer serializer = new InvoiceModelSerializer();

    public PrintServiceImpl() {
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void printBySelectedPrinter(Invoice invoice, int copys, javax.print.PrintService printer,
                                       String lang, boolean oryginal)
            throws RuntimeException, PrinterException, IOException {
        File file = createXMLTempFile(invoice);
        printBySelectedPrinter(file, copys, printer, lang, oryginal);
    }

    private File createXMLTempFile(Invoice invoice) {
        File tmpFile;
        try {
            tmpFile = File.createTempFile("invoice", ".xml");
            tmpFile.deleteOnExit();
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException("problem z utworzeniem pliku tymczasowego", ex);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("tmp invoice file " + tmpFile);
        }
        serializer.toFile(invoice, tmpFile.getAbsolutePath());
        return tmpFile;
    }

    public void printBySelectedPrinter(File xmlSource, int copys, javax.print.PrintService printer,
                                       String lang, boolean oryginal)
            throws RuntimeException, PrinterException, IOException {
        try {

            HashMap params = (HashMap) langUtil.loadLang(lang);

            params.put("oryginal", oryginal);
            params.put("lang", lang);

            JRXmlDataSource ds = new JRXmlDataSource(xmlSource, "/invoice");
            ds.setDatePattern("yyyy-MM-dd'Z'");
            ds.setNumberPattern("###0.00;-###0.00");
            ds.setLocale(Locale.US);

            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintService(printer);
            //job.printDialog();

            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            printRequestAttributeSet.add(new Copies(copys));

            //printRequestAttributeSet.add(MediaSizeName.ISO_A4);
            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(resource.getInputStream(), params, ds);

            JRPrintServiceExporter exporter;
            exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printer);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET,
                                  printer.getAttributes());
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET,
                                  printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG,
                                  Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG,
                                  Boolean.TRUE);
            exporter.exportReport();

        } catch (JRException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public void printByDefaultPrinter(Invoice invoice, int copys, String lang, boolean oryginal) {
        File file = createXMLTempFile(invoice);
        printByDefaultPrinter(file, copys, lang, oryginal);
    }

    public void printByDefaultPrinter(File xmlSource, int copys, String lang, boolean oryginal) {

        HashMap params = (HashMap) langUtil.loadLang(lang);

        params.put("oryginal", oryginal);
        params.put("lang", lang);
        printByDefaultPrinter(xmlSource, copys, "/invoice", params);

    }

    private void printByDefaultPrinter(File xmlSource, int copys, String selectExpression,
                                       HashMap params) {
        try {

            if (logger.isDebugEnabled()) {
                logger.debug("file to print " + xmlSource);
            }

            JRXmlDataSource ds = new JRXmlDataSource(xmlSource, selectExpression);

            ds.setDatePattern("yyyy-MM-dd'Z'");
            ds.setNumberPattern("###0.00;-###0.00");
            ds.setLocale(Locale.US);

            params.put(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, "#,##0.##");
            params.put(JRXPathQueryExecuterFactory.XML_LOCALE, Locale.US);

            JasperPrint print = null;

            if (resource != null) {
                try {
                    print = JasperFillManager.fillReport(resource.getInputStream(), params, ds);
                } catch (IOException ex) {
                    logger.error(ex.getMessage(), ex);
                    throw new RuntimeException(ex);
                }
            } else {
                throw new IllegalStateException("Brak pliku jasper - użyj metody setResource");
            }

            for (int i = 0; i < copys; i++) {
                JasperPrintManager.printReport(print, false);

            }

        } catch (JRException ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public void exportPDFi18n(Invoice invoice, String destFileName, String lang, boolean oryginal) {
        exportPDFi18n(createXMLTempFile(invoice), destFileName, lang, oryginal);
    }

    public void exportPDFi18n(File xmlSource, String destFileName, String lang, boolean oryginal) {
        HashMap params = (HashMap) langUtil.loadLang(lang);
        params.put("lang", lang);
        params.put("oryginal", oryginal);
        exportPDF(xmlSource, destFileName, "/invoice", params);
    }

    protected void exportPDF(File xmlSource, String destFileName, String selectExpression,
                             HashMap params) {

        if (logger.isDebugEnabled()) {
            logger.debug("file to export " + xmlSource);
        }

        try {
            JRXmlDataSource ds = new JRXmlDataSource(xmlSource, selectExpression);
            //ds.subDataSource("/suite/items/item");

            ds.setDatePattern("yyyy-MM-dd'Z'");
            ds.setNumberPattern("###0.00;-###0.00");
            ds.setLocale(Locale.US);

            JasperPrint print = null;

            if (resource != null) {
                try {
                    print = JasperFillManager.fillReport(resource.getInputStream(), params, ds);
                } catch (IOException ex) {
                    logger.error(ex.getMessage(), ex);
                    throw new RuntimeException(ex);
                }
            } else {
                throw new IllegalStateException("Brak pliku jasper - użyj metody setResource");
            }

            JasperExportManager.exportReportToPdfFile(print, destFileName);

        } catch (JRException ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public void exportPDFi18n(Invoice invoice, String destFileName, String lang) {
        exportPDFi18n(createXMLTempFile(invoice), destFileName, lang);
    }

    public void exportPDFi18n(File xmlSource, String destFileName, String lang) {
        HashMap params = (HashMap) langUtil.loadLang(lang);
        params.put("lang", lang);
        exportPDF(xmlSource, destFileName, "/invoice", params);
    }
}
