/**
 * Copyright 2016-01-21 the original author or authors.
 */
package pl.com.softproject.utils.excelcsv;

import com.csvreader.CsvWriter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author Adrian Lapierre {@literal <adrian@soft-project.pl>}
 */
public class ExcelToCsv {

    public static void main(String[] args) throws Exception {

        Options options = new Options();
        options.addOption("h", false, "help");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args );

        if(cmd.getArgs().length != 1) {
            System.out.println("Nieprawidłowa lista parametrów");
            printHelp(options);
        }

        if(cmd.hasOption("h")) {
            printHelp(options);
            System.exit(0);
        }

        File in = new File(cmd.getArgs()[0]);
        File out = new File(in.getParent(), "out.csv");
        convert(in, out);



    }

    private static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "excel2csv nazwapliku.xlsx", options );
    }

    private static void convert(File source, File dest) throws FileNotFoundException {

        FileInputStream input = null;
        input = new FileInputStream(source);

        CsvWriter writer = null;

        try {

            writer = new CsvWriter(dest.getAbsolutePath(), ',', Charset.forName("UTF-8"));
            writer.setTextQualifier('"');

            XSSFWorkbook workbook = new XSSFWorkbook(input);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if(cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                        String val = cell.getStringCellValue();
                        val = val.replaceAll("\"", "'");
                        val = val.trim();

                        writer.write(val, true);
                    } else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                        double val = cell.getNumericCellValue();
                        writer.write(String.valueOf(val));
                    }
                }
                writer.endRecord();
            }


        } catch (IOException ex) {
            throw new RuntimeException("problem z dostępem do pliku", ex);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                if(writer != null) {
                    writer.close();
                }
            } catch (IOException ignore) {
                System.out.println("problem z zakmnięciem pliku");
            }
        }

    }

}
