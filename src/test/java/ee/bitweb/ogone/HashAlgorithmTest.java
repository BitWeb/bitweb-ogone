package ee.bitweb.ogone;

import org.junit.Assert;
import org.junit.Test;

public class HashAlgorithmTest {

    @Test(expected = IllegalArgumentException.class)
    public void testHashAlgorithmNotPermitted() {
        String notSupportHashAlgorithm = "md5";
        HashAlgorithm hashAlgorithm = new HashAlgorithm(notSupportHashAlgorithm);
        Assert.assertEquals(hashAlgorithm.toString(), notSupportHashAlgorithm);
    }

}
