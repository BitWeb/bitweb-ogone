package ee.bitweb.ogone.directLink;

import ee.bitweb.ogone.AbstractPaymentResponse;
import ee.bitweb.ogone.exceptions.XmlParseException;

public class DirectLinkPaymentResponse extends AbstractPaymentResponse {

    public DirectLinkPaymentResponse(String xmlString) throws XmlParseException {
        super(xmlString);
    }

}
