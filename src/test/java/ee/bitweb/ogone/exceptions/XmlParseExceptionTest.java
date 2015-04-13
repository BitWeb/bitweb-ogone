package ee.bitweb.ogone.exceptions;

import ee.bitweb.ogone.directLink.Alias;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by priit on 30.03.15.
 */
public class XmlParseExceptionTest {

    @Test
    public void testCreate() {
        new XmlParseException();
        new XmlParseException("Test text");
        new XmlParseException("Test text", new Throwable());
    }
}
