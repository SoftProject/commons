package pl.com.softproject.utils.pivot.doublepivot;

import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Class PivotDoubleGroupingTableModelImpl
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class PivotDoubleGroupingTableModelImpl implements PivotDoubleGroupingTableModel {

    private LinkedHashSet<Row> rows = new LinkedHashSet<Row>();
    private RowIterator rowIterator = (RowIterator) iterator();

    @Override
    public void addRow(final Row row) {
        rows.add(row);
    }

    @Override
    public void addRows(final LinkedHashSet<Row> rows) {
        for (final Row row : rows) {
            rows.add(row);
        }
    }

    @Override
    public int getRowsCount() {
        return rows.size();
    }

    @Override
    public LinkedHashSet<String> getRowNames() {
        LinkedHashSet<String> names = new LinkedHashSet<String>(rows.size());
        while (rowIterator.hasNext()) {
            names.add(rowIterator.next().getName());
        }
        return names;
    }

    @Override
    public boolean contains(final String elementKey) {
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getKey().equals(elementKey)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Row getRow(final String rowKey) {
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getKey().equals(rowKey)) {
                return row;
            }
        }
        return null;
    }

    @Override
    public void removeRow(final String rowKey) {
        while (rowIterator.hasNext()) {
            Row next = rowIterator.next();
            if (next.getKey().equals(rowKey)) {
                rowIterator.remove();
            }
        }
    }

    @Override
    public void addSubRow(final String rowKey, final SubRow subRow) {

    }

    @Override
    public void addSubRows(final String rowKey, final LinkedHashSet<SubRow> subRows) {

    }

    @Override
    public int getSubRowCount(final String rowKey) {
        return 0;
    }

    @Override
    public LinkedHashSet<String> getSubRowNames(final String rowKey) {
        return null;
    }

    @Override
    public SubRow getSubRow(final String rowKey, final String subRowKey) {
        return null;
    }

    @Override
    public void removeSubRow(final String rowKey, final String subRowKey) {
    }

    @Override
    public void addColumn(final String rowKey, final String subRowKey, final Column column) {

    }

    @Override
    public void addColumns(final String rowKey, final String subRowKey,
                           final LinkedHashSet<Column> columns) {

    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public LinkedHashSet<String> getColumnNames() {
        return null;
    }

    @Override
    public Column getColumn(final String rowKey, final String subRowKey) {
        return null;
    }

    @Override
    public void removeColumn(final String rowKey, final String subRowKey) {
    }

    @Override
    public Class getColumnClass(final String rowKey, final String subRowKey) {
        return null;
    }

    @Override
    public Object get(final String rowKey, final String subRowKey, final String columnKey) {
        return null;
    }

    @Override
    public Iterator iterator() {
        return new RowIterator(this);
    }

    public static class RowIterator implements Iterator<Row> {

        private final Iterator<Row> iterator;

        RowIterator(PivotDoubleGroupingTableModelImpl pivotDoubleTableModel) {
            iterator = pivotDoubleTableModel.rows.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Row next() {
            return iterator.next();
        }

        @Override
        public void remove() {
            iterator.remove();
        }
    }
}
