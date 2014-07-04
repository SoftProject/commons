package pl.com.softproject.emailintegration.model;

/**
 *
 * @author rafalc
 */
public class TestObject {

    private String emailAddress;
    private String userName;

    public TestObject(String emailAddress, String userName) {
        this.emailAddress = emailAddress;
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUserName() {
        return userName;
    }
}
