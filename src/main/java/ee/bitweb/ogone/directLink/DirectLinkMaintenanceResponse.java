package ee.bitweb.ogone.directLink;

import ee.bitweb.ogone.AbstractResponse;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class DirectLinkMaintenanceResponse extends DirectLinkPaymentResponse {

    DirectLinkMaintenanceResponse(String xmlString) {
        super(xmlString);
    }

    public boolean isSuccessful() {
        return (getParameter("NCERROR").toString().equals("0"));
    }

    protected Map<String, Object> filterRequestParameters(Map<String, Object> httpRequest) {
        ArrayList<String> fields = new ArrayList<String>();
        for (String field : ogoneFields) {
            fields.add(field);
        }
        fields.add("NCERRORPLUS");
        fields.add("PAYIDSUB");
        httpRequest.keySet().retainAll(fields);
        return httpRequest;
    }

}
