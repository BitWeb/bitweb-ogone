package ee.bitweb.ogone;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by priit on 30.03.15.
 */
public class PassphraseTest {

    @Test
    public void testToString() {
        Passphrase pass = new Passphrase("TEST");
        Assert.assertEquals(pass.toString(), "TEST");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgument() {
        new Passphrase(null);
    }
}
