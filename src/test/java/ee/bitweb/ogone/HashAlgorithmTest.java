package ee.bitweb.ogone;

import org.junit.Assert;
import org.junit.Test;

public class HashAlgorithmTest {

    @Test
    public void testToString() {
        HashAlgorithm hashAlgorithm = new HashAlgorithm(HashAlgorithm.HASH_SHA1);
        Assert.assertEquals(hashAlgorithm.toString(), HashAlgorithm.HASH_SHA1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHashAlgorithmNotPermitted() {
        String notSupportHashAlgorithm = "md5";
        HashAlgorithm hashAlgorithm = new HashAlgorithm(notSupportHashAlgorithm);
        Assert.assertEquals(hashAlgorithm.toString(), notSupportHashAlgorithm);
    }
}
