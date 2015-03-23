package ee.bitweb.ogone.directLink;

import ee.bitweb.ogone.AbstractPaymentResponse;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class DirectLinkQueryResponse extends AbstractPaymentResponse {

    public DirectLinkQueryResponse(String xmlString) {
        super(xmlString);
    }

    public boolean isSuccessful() {
        return (getParameter("NCERROR").equals("0"));
    }

    protected Map<String, Object> filterRequestParameters(Map<String, Object> httpRequest) {
        ArrayList<String> fields = new ArrayList<String>();
        for (String field : ogoneFields) {
            fields.add(field);
        }
        fields.add("PAYIDSUB");
        fields.add("NCSTATUS");
        fields.add("'NCERRORPLUS'");
        httpRequest.keySet().retainAll(fields);
        return httpRequest;
    }

}
