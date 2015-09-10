package pl.com.softproject.utils.pivot.doublepivot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Class PivotDoubleGroupingExcelExporterTest
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
@RunWith(JUnit4.class)
public class PivotDoubleGroupingExcelExporterTest {

    @Test
    public void testWorkBook() throws Exception {

        PivotDoubleGroupingTableModelImpl pivotDoubleGroupingTableModel =
                new PivotDoubleGroupingTableModelImpl();

        List<Column> columns =
                Arrays.asList(new Column("col1", "2013", 12), new Column("col2", "2014", 15),
                              new Column("col3", "2015", 18),
                              new Column("total", "Total sum", 123));

        List<SubRow> subRows = Arrays.asList(
                new SubRow("mkjasinski", "Marcin Jasiński", new LinkedHashSet<Column>(columns)),
                new SubRow("irekas", "Izabela Rękas", new LinkedHashSet<Column>()),
                new SubRow("total sum", "total sum", new LinkedHashSet<Column>(columns)));

        List<SubRow> subRows2 = Arrays.asList(
                new SubRow("mkjasinski", "Marcin Jasiński", new LinkedHashSet<Column>(columns)),
                new SubRow("irekas", "Izabela Rękas", new LinkedHashSet<Column>()),
                new SubRow("alapierre", "Adrian Lapierre", new LinkedHashSet<Column>(columns)),
                new SubRow("total sum", "total sum", new LinkedHashSet<Column>(columns)));

        List<Row> rows =
                Arrays.asList(new Row("PUB1", "Publikacja 1", new LinkedHashSet<SubRow>(subRows)),
                              new Row("PUB2", "Publikacja 2", new LinkedHashSet<SubRow>(subRows2)),
                              new Row("PUB3", "Publikacja 3", new LinkedHashSet<SubRow>(subRows)),
                              new Row("PUB4", "Publikacja 4", new LinkedHashSet<SubRow>(subRows2)),
                              new Row("PUB5", "Publikacja 5", new LinkedHashSet<SubRow>(subRows)),
                              new Row("PUB6", "Publikacja 6", new LinkedHashSet<SubRow>(subRows2)));

        pivotDoubleGroupingTableModel.addRows(new LinkedHashSet<Row>(rows));

        PivotDoubleGroupingExcelExporter pivotDoubleGroupingExcelExporter =
                new PivotDoubleGroupingExcelExporter();
        pivotDoubleGroupingExcelExporter.export(pivotDoubleGroupingTableModel, "name");

        File workBookFile = File.createTempFile("name", ".xls");
        pivotDoubleGroupingExcelExporter.saveWorkbook(workBookFile);
    }
}
