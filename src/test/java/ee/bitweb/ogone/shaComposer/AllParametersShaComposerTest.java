package ee.bitweb.ogone.shaComposer;


import ee.bitweb.ogone.HashAlgorithm;
import ee.bitweb.ogone.Passphrase;
import ee.bitweb.ogone.exceptions.ShaComposerException;
import ee.bitweb.ogone.parameterFilter.GeneralParameterFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by priit on 30.03.15.
 */
public class AllParametersShaComposerTest {

    @Test
    public void testConstruct() {
        new AllParametersShaComposer();
        new AllParametersShaComposer( new Passphrase("PASS"));
        new AllParametersShaComposer( new Passphrase("PASS"), new HashAlgorithm(HashAlgorithm.HASH_SHA1));
    }

    @Test
    public void testAddParametersFilter() {
        AllParametersShaComposer shaComposer = new AllParametersShaComposer();
        shaComposer.addParameterFilter( new GeneralParameterFilter());
    }

    @Test
    public void testCompose(){

        AllParametersShaComposer shaComposer = new AllParametersShaComposer( new Passphrase("P"), new HashAlgorithm(HashAlgorithm.HASH_SHA1) );
        shaComposer.addParameterFilter( new GeneralParameterFilter());
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("X", "T");
        parameters.put("Y", null);

        try {
            shaComposer.compose(parameters);
        } catch (ShaComposerException e) {
            e.printStackTrace();
        }
    }
}
