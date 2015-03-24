package ee.bitweb.ogone;

import ee.bitweb.ogone.exceptions.XmlParseException;

public abstract class AbstractPaymentResponse extends AbstractResponse {

    public AbstractPaymentResponse(String xmlString) throws XmlParseException {
        super(xmlString);
    }

    public Double getAmount() {
        String amount = ((String) getParameter("amount"));
        if (amount == null) {
            return 0.0;
        }
        try {
            return Double.valueOf(amount);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public boolean isSuccessful() {
        String status = (String) getParameter("STATUS");
        Integer statusAsInt = Integer.valueOf(status);
        return statusAsInt == PaymentResponse.STATUS_AUTHORISED || statusAsInt == PaymentResponse.STATUS_PAYMENT_REQUESTED;
    }

}
