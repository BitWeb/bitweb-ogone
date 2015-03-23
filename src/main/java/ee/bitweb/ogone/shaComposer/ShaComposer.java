package ee.bitweb.ogone.shaComposer;

import ee.bitweb.ogone.exceptions.ShaComposerException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface ShaComposer {

    public String compose(Map<String, Object> parameters) throws ShaComposerException;

}
