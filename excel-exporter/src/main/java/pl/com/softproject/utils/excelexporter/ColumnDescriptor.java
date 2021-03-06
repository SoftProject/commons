/*
 * Copyright 2011-03-30 the original author or authors.
 */

package pl.com.softproject.utils.excelexporter;

/**
 * Opisuje kolumnę w tworzonym pliku .xls
 *
 * @author <a href="mailto:alapierre@soft-project.pl">Adrian Lapierre</a>
 * $Rev: $, $LastChangedBy: $
 * $LastChangedDate: $
 */
public class ColumnDescriptor {

    private String headerName;
    private String propertyName;
    private String excelFormatMask;
    private ColumnValueFormatter columnValueFormatter;

    /**
     *
     * @param headerName - nazwa kolumny widoczna w nagłówku
     * @param propertyName - nazwa property
     */
    public ColumnDescriptor(String headerName, String propertyName) {
        this.headerName = headerName;
        this.propertyName = propertyName;
    }

    public ColumnDescriptor(String headerName, String propertyName, String excelFormatMask) {
        this.headerName = headerName;
        this.propertyName = propertyName;
        this.excelFormatMask = excelFormatMask;
    }
    
    public ColumnDescriptor(String headerName, String propertyName, ColumnValueFormatter formatter) {
        this.headerName = headerName;
        this.propertyName = propertyName;
        this.columnValueFormatter = formatter;
    }

    public String getExcelFormatMask() {
        return excelFormatMask;
    }
    
    public String getHeaderName() {
        return headerName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public boolean isMaskSet() {
        return excelFormatMask != null;
    }

    public ColumnValueFormatter getColumnValueFormatter() {
        return columnValueFormatter;
    }

    public void setColumnValueFormatter(ColumnValueFormatter columnValueFormatter) {
        this.columnValueFormatter = columnValueFormatter;
    }

}
