package pl.com.softproject.utils.payu.model;

/**
 *
 * @author Leszek Bednorz <lbednorz@softproject.com.pl>
 */
public enum PaymentStatus {

    S1("1", "nowa"),
    S2("2", "anulowana"),
    S3("3", "odrzucona"),
    S4("4", "rozpoczeta"),
    S5("5", "oczekuje na odbior"),
    S6("6", "autoryzacja odmowna"),
    S7("7", "platnosc odrzucona"),
    S99("99", "platnosc odebrana - zakonczona"),
    S888("888", "bledny status"), S0("0", "błąd");

    private String code;
    private String message;
    private ErrorMessage error;

    private PaymentStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public static PaymentStatus getByCode(String code) {
        return valueOf("S" + code);
    }

    public ErrorMessage getError() {
        return this.error;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }

    public String toString() {
        return "PaymentStatus{code=" + this.code + ", message=" + this.message + ", error=" + this.error + '}';
    }
}
