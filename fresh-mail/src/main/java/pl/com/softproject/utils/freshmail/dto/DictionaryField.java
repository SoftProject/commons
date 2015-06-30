package pl.com.softproject.utils.freshmail.dto;

import java.io.Serializable;

/**
 * Class DictionaryField
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class DictionaryField implements Serializable {

    private String fieldName;

    private String fieldValue;

    public DictionaryField(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String toString() {
        return "DictionaryField{" +
               "fieldName='" + fieldName + '\'' +
               ", fieldValue='" + fieldValue + '\'' +
               '}';
    }
}
