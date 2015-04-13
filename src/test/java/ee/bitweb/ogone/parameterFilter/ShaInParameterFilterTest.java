package ee.bitweb.ogone.parameterFilter;

import ee.bitweb.ogone.directLink.DirectLinkMaintenanceRequest;
import ee.bitweb.ogone.shaComposer.MockShaComposer;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by priit on 30.03.15.
 */
public class ShaInParameterFilterTest {

    @Test
    public void testConstruct() {
        ShaInParameterFilter parameterFilter = new ShaInParameterFilter();

        HashSet<String> ignoreShaParameters = new HashSet<String>();
        ignoreShaParameters.add("ADDMATCH");
        parameterFilter.ignoreShaParameters = ignoreShaParameters;

        Map<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("ACCEPTURL","URL");
        parameters.put("ADDMATCH","Tello");
        parameters.put("RANDOM","RANDOM");

        Map<String, Object> result = parameterFilter.filter(parameters);

        Assert.assertEquals("URL", result.get("ACCEPTURL"));
        Assert.assertEquals(null, result.get("ADDMATCH"));
        Assert.assertEquals(null, result.get("RANDOM"));
    }

}
