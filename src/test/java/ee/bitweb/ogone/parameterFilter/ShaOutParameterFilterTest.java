package ee.bitweb.ogone.parameterFilter;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by priit on 30.03.15.
 */
public class ShaOutParameterFilterTest {

    @Test
    public void testConstruct() {
        ShaOutParameterFilter parameterFilter = new ShaOutParameterFilter();

        Map<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("AAVADDRESS","URL");
        parameters.put("RANDOM","RANDOM");

        Map<String, Object> result = parameterFilter.filter(parameters);

        Assert.assertEquals("URL", result.get("AAVADDRESS"));
        Assert.assertEquals(null, result.get("RANDOM"));
    }

}
