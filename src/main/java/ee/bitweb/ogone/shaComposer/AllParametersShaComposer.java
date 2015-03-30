package ee.bitweb.ogone.shaComposer;

import ee.bitweb.ogone.HashAlgorithm;
import ee.bitweb.ogone.Passphrase;
import ee.bitweb.ogone.exceptions.ShaComposerException;
import ee.bitweb.ogone.parameterFilter.GeneralParameterFilter;
import ee.bitweb.ogone.parameterFilter.ParameterFilter;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllParametersShaComposer implements ShaComposer {

    private List<ParameterFilter> parameterFilters = new ArrayList<ParameterFilter>();
    private Passphrase passphrase;
    private HashAlgorithm hashAlgorithm;

    public AllParametersShaComposer() {
    }

    public AllParametersShaComposer(Passphrase passphrase) {
        this.parameterFilters.add(new GeneralParameterFilter());
        this.passphrase = passphrase;
        this.hashAlgorithm = new HashAlgorithm(HashAlgorithm.HASH_SHA1);
    }

    public AllParametersShaComposer(Passphrase passphrase, HashAlgorithm hashAlgorithm) {
        this(passphrase);
        this.hashAlgorithm = hashAlgorithm;
    }

    /**
     * Requirements for SHASIGN:sup
     *
     * All parameter names should be in UPPERCASE (to avoid any case confusion)
     * All parameters have to be arranged alphabetically
     * Parameters that do not have a value should NOT be included in the string to hash
     * A string hashed with the SHA-1 algorithm will always be 40 characters long
     */
    public String compose(Map<String, Object> parameters) throws ShaComposerException {
        for (ParameterFilter parameterFilter : parameterFilters) {
            parameters = parameterFilter.filter(parameters);
        }
        String shaString = "";
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            if (entry.getValue() == null || entry.getValue() == "") {
                continue;
            }
            shaString += entry.getKey() + "=" + entry.getValue().toString() + passphrase.toString();
        }

        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance(hashAlgorithm.toString());
            md.update(shaString.getBytes("UTF-8"));
            result = new BigInteger(1, md.digest()).toString(16);
            while (result.length() < 40) {
                result = "0" + result;
            }
        } catch(NoSuchAlgorithmException e1) {
            throw new ShaComposerException(e1.getMessage(), e1);
        } catch (UnsupportedEncodingException e2) {
            throw new ShaComposerException(e2.getMessage(), e2);
        }

        return result.toUpperCase();
    }

    public void addParameterFilter(ParameterFilter parameterFilter) {
        parameterFilters.add(parameterFilter);
    }

}
