package pl.com.softproject.utils.pivot.doublepivot;

/**
 * Class Column
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class Column implements Cell {

    private String key;
    private String name;
    private Object value;

    public Column() {
    }

    public Column(final String key, final String name, final Object value) {
        this.key = key;
        this.name = name;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(final Object value) {
        this.value = value;
    }
}
