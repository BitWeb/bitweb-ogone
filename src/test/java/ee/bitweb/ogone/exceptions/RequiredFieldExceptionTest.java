package ee.bitweb.ogone.exceptions;

import org.junit.Test;

/**
 * Created by priit on 30.03.15.
 */
public class RequiredFieldExceptionTest {
    @Test
    public void testCreate() {
        new RequiredFieldException();
        new RequiredFieldException("Test text");
        new RequiredFieldException("Test text", new Throwable());
    }
}
