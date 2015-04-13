package ee.bitweb.ogone.exceptions;

import org.junit.Test;

/**
 * Created by priit on 30.03.15.
 */
public class ShaComposerExceptionTest {

    @Test
    public void testCreate() {
        new ShaComposerException();
        new ShaComposerException("Test text");
        new ShaComposerException("Test text", new Throwable());
    }

}
