package ee.bitweb.ogone.parameterFilter;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class GeneralParameterFilter implements ParameterFilter {

    public Map<String, Object> filter(Map<String, Object> parameters) {
        Map<String, Object> filteredParameters = new TreeMap<String, Object>();
        for (Map.Entry entry : parameters.entrySet()) {
            filteredParameters.put(entry.getKey().toString().toUpperCase(), entry.getValue());
        }
        return filteredParameters;
    }

}
