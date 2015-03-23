package ee.bitweb.ogone.parameterFilter;

import java.util.Map;

public interface ParameterFilter {

    public Map<String, Object> filter(Map<String, Object> parameters);

}
