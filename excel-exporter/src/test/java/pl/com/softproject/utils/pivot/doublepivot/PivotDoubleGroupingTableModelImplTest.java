package pl.com.softproject.utils.pivot.doublepivot;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pl.com.softproject.utils.pivot.doublepivot.exception.RowNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Class PivotDoubleTableModelImplTest
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
@RunWith(JUnit4.class)
public class PivotDoubleGroupingTableModelImplTest extends TestCase {

    private PivotDoubleGroupingTableModelImpl pivotDoubleTableModel;

    @Test
    public void testAddRow() throws Exception {
        prepareObject();

        Row row = new Row("key", "name", new LinkedHashSet<SubRow>());

        pivotDoubleTableModel.addRow(row);

        assertEquals(1, pivotDoubleTableModel.getRowsCount());
    }

    @Test
    public void testAddRows() throws Exception {
        prepareObject();

        List<Row> rows = Arrays.asList(new Row("key", "name", new LinkedHashSet<SubRow>()),
                                       new Row("key1", "name1", new LinkedHashSet<SubRow>()));

        pivotDoubleTableModel.addRows(new LinkedHashSet<Row>(rows));

        assertEquals(2, pivotDoubleTableModel.getRowsCount());
    }

    @Test
    public void testGetRowsCount() throws Exception {
        prepareObject();

        assertEquals(0, pivotDoubleTableModel.getRowsCount());
    }

    @Test
    public void testGetRowNames() throws Exception {
        prepareObject();

        Row row = new Row("key", "name", new LinkedHashSet<SubRow>());

        pivotDoubleTableModel.addRow(row);

        LinkedHashSet<String> rowNames = pivotDoubleTableModel.getRowNames();

        LinkedHashSet<String> expected =
                new LinkedHashSet<String>(Collections.singletonList("name"));

        assertEquals(expected, rowNames);
    }

    @Test
    public void testContainsRow() throws Exception {
        prepareObject();

        Row row = new Row("key", "name", new LinkedHashSet<SubRow>());

        pivotDoubleTableModel.addRow(row);

        assertTrue(pivotDoubleTableModel.contains("key"));
    }

    @Test
    public void testGetRow() throws Exception {
        prepareObject();

        Row row = new Row("key", "name", new LinkedHashSet<SubRow>());

        pivotDoubleTableModel.addRow(row);

        Row newRow = pivotDoubleTableModel.getRow("key");

        assertEquals(newRow, row);
    }

    @Test
    public void testRemoveRow() throws Exception {
        prepareObject();

        List<Row> rows = Arrays.asList(new Row("key", "name", new LinkedHashSet<SubRow>()),
                                       new Row("key1", "name1", new LinkedHashSet<SubRow>()));

        pivotDoubleTableModel.addRows(new LinkedHashSet<Row>(rows));

        pivotDoubleTableModel.removeRow("key");

        assertEquals(1, pivotDoubleTableModel.getRowsCount());
    }

    @Test(expected = RowNotFoundException.class)
    public void testAddSubRowException() throws Exception {
        prepareRows();

        SubRow subRow = new SubRow("subRow1", "subRowName1", new LinkedHashSet<Column>());

        pivotDoubleTableModel.addSubRow("key2", subRow);
    }

    @Test
    public void testAddSubRow() throws Exception {
        prepareRows();

        SubRow subRow = new SubRow("subRow1", "subRowName1", new LinkedHashSet<Column>());

        pivotDoubleTableModel.addSubRow("key1", subRow);

        assertEquals(1, pivotDoubleTableModel.getSubRowCount("key1"));
    }

    @Test
    public void testAddSubRows() throws Exception {
        prepareRows();

        List<SubRow> subRows =
                Arrays.asList(new SubRow("subRow1", "subRowName1", new LinkedHashSet<Column>()),
                              new SubRow("subRow2", "subRowName2", new LinkedHashSet<Column>()));

        pivotDoubleTableModel.addSubRows("key1", new LinkedHashSet<SubRow>(subRows));

        assertEquals(2, pivotDoubleTableModel.getSubRowCount("key1"));
    }

    @Test
    public void testGetSubRowCount() throws Exception {
        prepareRows();

        assertEquals(0, pivotDoubleTableModel.getSubRowCount("key1"));
    }

    @Test
    public void testGetSubRowNames() throws Exception {
        prepareRows();

        List<SubRow> subRows =
                Arrays.asList(new SubRow("subRow1", "subRowName1", new LinkedHashSet<Column>()),
                              new SubRow("subRow2", "subRowName2", new LinkedHashSet<Column>()));

        pivotDoubleTableModel.addSubRows("key1", new LinkedHashSet<SubRow>(subRows));

        LinkedHashSet<String> subRowNames = pivotDoubleTableModel.getSubRowNames("key1");

        LinkedHashSet<String> expected =
                new LinkedHashSet<String>(Arrays.asList("subRowName1", "subRowName2"));

        assertEquals(expected, subRowNames);
    }

    @Test
    public void testGetSubRow() throws Exception {
        prepareRows();

        SubRow subRow = new SubRow("subRow1", "subRowName1", new LinkedHashSet<Column>());

        pivotDoubleTableModel.addSubRow("key1", subRow);

        SubRow newSubRow = pivotDoubleTableModel.getSubRow("key1", "subRow1");

        assertEquals(newSubRow, subRow);
    }

    @Test
    public void testRemoveSubRow() throws Exception {
        prepareRows();

        List<SubRow> subRows =
                Arrays.asList(new SubRow("subRow1", "subRowName1", new LinkedHashSet<Column>()),
                              new SubRow("subRow2", "subRowName2", new LinkedHashSet<Column>()));

        pivotDoubleTableModel.addSubRows("key1", new LinkedHashSet<SubRow>(subRows));

        pivotDoubleTableModel.removeSubRow("key1", "subRow1");

        assertEquals(1, pivotDoubleTableModel.getSubRowCount("key1"));
    }

    @Test
    public void testAddColumn() throws Exception {
        prepareSubRows();

        Column column = new Column("col1", "colName1", "v1");

        pivotDoubleTableModel.addColumn("key", "subRow1", column);

        assertEquals(1, pivotDoubleTableModel.getColumnCount());
    }

    @Test
    public void testAddColumns() throws Exception {
        prepareSubRows();

        List<Column> columns = Arrays.asList(new Column("col1", "colName1", "v1"),
                                             new Column("col2", "colName2", "v2"));

        pivotDoubleTableModel.addColumns("key", "subRow1", new LinkedHashSet<Column>(columns));

        assertEquals(2, pivotDoubleTableModel.getColumnCount());
    }

    @Test
    public void testGetColumnCount() throws Exception {
        prepareSubRows();

        assertEquals(0, pivotDoubleTableModel.getColumnCount());
    }

    @Test
    public void testGetColumnNames() throws Exception {
        prepareSubRows();

        List<Column> columns = Arrays.asList(new Column("col1", "colName1", "v1"),
                                             new Column("col2", "colName2", "v2"));

        pivotDoubleTableModel.addColumns("key", "subRow1", new LinkedHashSet<Column>(columns));

        LinkedHashSet<String> columnNames = pivotDoubleTableModel.getColumnNames();

        LinkedHashSet<String> expectedColumnNames =
                new LinkedHashSet<String>(Arrays.asList("colName1", "colName2"));

        assertEquals(expectedColumnNames, columnNames);
    }

    @Test
    public void testGetColumn() throws Exception {
        prepareSubRows();

        Column column = new Column("col1", "colName1", "v1");

        pivotDoubleTableModel.addColumn("key", "subRow1", column);

        Column expectedColumn = pivotDoubleTableModel.getColumn("key", "subRow1", "col1");

        assertEquals(expectedColumn, column);
    }

    @Test
    public void testRemoveColumn() throws Exception {
        prepareSubRows();

        List<Column> columns = Arrays.asList(new Column("col1", "colName1", "v1"),
                                             new Column("col2", "colName2", "v2"));

        pivotDoubleTableModel.addColumns("key", "subRow1", new LinkedHashSet<Column>(columns));

        pivotDoubleTableModel.removeColumn("key", "subRow1", "col1");

        assertEquals(1, pivotDoubleTableModel.getColumnCount());
    }

    @Test
    public void testGetColumnClass() throws Exception {
        prepareSubRows();

        Column column = new Column("col1", "colName1", "v1");

        pivotDoubleTableModel.addColumn("key", "subRow1", column);

        Class columnClass = pivotDoubleTableModel.getColumnClass("key", "subRow1", "col1");

        assertEquals(String.class, columnClass);
    }

    @Test
    public void testGet() throws Exception {
        prepareSubRows();

        Column column = new Column("col1", "colName1", 12);

        pivotDoubleTableModel.addColumn("key", "subRow1", column);

        Object columnValue = pivotDoubleTableModel.get("key", "subRow1", "col1");

        assertEquals(Integer.class, columnValue.getClass());
        assertEquals(12, columnValue);
    }

    @Test
    public void testIterator() throws Exception {
        prepareObject();

        Iterator iterator = pivotDoubleTableModel.iterator();

        assertTrue(iterator instanceof PivotDoubleGroupingTableModelImpl.RowIterator);
    }

    private void prepareObject() {
        pivotDoubleTableModel = new PivotDoubleGroupingTableModelImpl();
    }

    private void prepareRows() {
        prepareObject();

        List<Row> rows = Arrays.asList(new Row("key", "name", new LinkedHashSet<SubRow>()),
                                       new Row("key1", "name1", new LinkedHashSet<SubRow>()));

        pivotDoubleTableModel.addRows(new LinkedHashSet<Row>(rows));
    }

    private void prepareSubRows() {
        prepareRows();

        List<SubRow> subRows =
                Arrays.asList(new SubRow("subRow1", "subRowName1", new LinkedHashSet<Column>()),
                              new SubRow("subRow2", "subRowName2", new LinkedHashSet<Column>()));

        pivotDoubleTableModel.addSubRows("key", new LinkedHashSet<SubRow>(subRows));
    }
}
