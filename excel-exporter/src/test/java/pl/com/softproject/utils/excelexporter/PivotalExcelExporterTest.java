package pl.com.softproject.utils.excelexporter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pl.com.softproject.utils.pivot.PivotTableExcelExporter;
import pl.com.softproject.utils.pivot.PivotTableModelImpl;

import java.awt.*;
import java.io.File;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class PivotalExcelExporterTest
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
@RunWith(JUnit4.class)
public class PivotalExcelExporterTest implements Serializable {

    @Test
    public void testExcelExporter() throws Exception {

        PivotTableModelImpl pivotTableModel = new PivotTableModelImpl();

        Map<String, Object> row1 = new LinkedHashMap<String, Object>();
        row1.put("pierwsza", "11");
        row1.put("druga", "12");
        row1.put("trzecia", "13");

        pivotTableModel.addRow("pierwszy", row1);

        PivotTableExcelExporter pivotTableExcelExporter = new PivotTableExcelExporter();
        pivotTableExcelExporter
                .export(pivotTableModel, "name", new String[]{"pierwsza", "druga", "trzecia"});

        File test = File.createTempFile("test", ".xls");
        pivotTableExcelExporter.saveWorkbook(test);

        Desktop.getDesktop().open(test);
    }
}
