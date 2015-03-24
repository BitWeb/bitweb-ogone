package ee.bitweb.ogone.exceptions;

public class RequiredFieldException extends Exception {

    public RequiredFieldException() {
    }

    public RequiredFieldException(String message) {
        super(message);
    }

    public RequiredFieldException(String message, Throwable cause) {
        super(message, cause);
    }

}
