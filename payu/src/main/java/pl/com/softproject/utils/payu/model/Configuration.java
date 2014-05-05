package pl.com.softproject.utils.payu.model;

/**
 *
 * @author Leszek Bednorz <lbednorz@softproject.com.pl>
 */
public class Configuration {

    private String posId;
    private String posAuthKey;
    private String key1;
    private String key2;

    public String getPosId() {
        return this.posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getPosAuthKey() {
        return this.posAuthKey;
    }

    public void setPosAuthKey(String posAuthKey) {
        this.posAuthKey = posAuthKey;
    }

    public String getKey1() {
        return this.key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getKey2() {
        return this.key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public Configuration(String posId, String posAuthKey, String key1, String key2) {
        this.posId = posId;
        this.posAuthKey = posAuthKey;
        this.key1 = key1;
        this.key2 = key2;
    }
}
