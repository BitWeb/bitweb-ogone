package ee.bitweb.ogone;

import java.util.Arrays;

public class HashAlgorithm {

    public static final String HASH_SHA1    = "sha1";
    public static final String HASH_SHA256  = "sha256";
    public static final String HASH_SHA512  = "sha512";
    public String[] allowedAlgorithms = {HASH_SHA1, HASH_SHA256, HASH_SHA512};

    private String algorithm;

    public HashAlgorithm(String algorithm) {
        if (!Arrays.asList(allowedAlgorithms).contains(algorithm)) {
            throw new IllegalArgumentException(algorithm + " is not permitted. ");
        }
        this.algorithm = algorithm;
    }

    public String toString() {
        return algorithm;
    }

}
