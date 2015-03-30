package ee.bitweb.ogone.directLink;

import ee.bitweb.ogone.exceptions.XmlParseException;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by priit on 30.03.15.
 */
public class DirectLinkPaymentResponseTest {

    @Test
    public void testSuccess() {
        try {
            DirectLinkPaymentResponse response = new DirectLinkPaymentResponse( getRequestXmlResponse() );
            Assert.assertTrue(response.isSuccessful());
            DirectLinkPaymentResponse responseNotOk = new DirectLinkPaymentResponse(getErrorRequestXmlResponse());
            Assert.assertFalse(responseNotOk.isSuccessful());

        } catch (XmlParseException e) {
        }
    }

    @Test
    public void testParseException() {

        Boolean hadException = false;

        try{
            DirectLinkPaymentResponse response = new DirectLinkPaymentResponse( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<ncresponse/></>" );
            response.getAmount();
        } catch (XmlParseException e) {
            hadException = true;
        }
        Assert.assertTrue(hadException);
    }


    @Test
    public void testGetAmount() {
        try{
            DirectLinkPaymentResponse response = new DirectLinkPaymentResponse( getRequestXmlResponse() );
            response.getAmount();
        } catch (XmlParseException e) {
        }
    }

    @Test
    public void testGetAmountFromInvalidValue() {
        try{
            DirectLinkPaymentResponse response = new DirectLinkPaymentResponse( getErrorRequestXmlResponse() );
            response.getAmount();
        } catch (XmlParseException e) {
        }
    }

    @Test
    public void testGetAmountNoValue() {
        try{
            String xml = getRequestXmlResponse();
            xml = xml.replace("amount=\"10\"", "");
            DirectLinkPaymentResponse response = new DirectLinkPaymentResponse( xml );
            response.getAmount();
        } catch (XmlParseException e) {
        }
    }

    private String getRequestXmlResponse() {

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<ncresponse STATUS=\"9\" " +
                "orderId=\"ORDER-1\" " +
                "ED=\"0616\" NCERRORED=\"0\" " +
                "CVC=\"XXX\" " +
                "CARDNO=\"XXXXXXXXXXXX1111\" " +
                "NCERRORCARDNO=\"0\" " +
                "ALIAS=\"ABCDE-12345\" " +
                "NCERROR=\"0\" " +
                "CN=\"Johann Voldemar Jannsen\" " +
                "NCERRORCN=\"0\" " +
                "amount=\"10\" " +
                "NCERRORCVC=\"0\"/>";
    }

    private String getErrorRequestXmlResponse() {

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<ncresponse STATUS=\"1\" " +
                "orderId=\"ORDER-1\" " +
                "ED=\"0616\" NCERRORED=\"1\" " +
                "CVC=\"XXX\" " +
                "CARDNO=\"XXXXXXXXXXXX1111\" " +
                "NCERRORCARDNO=\"1\" " +
                "ALIAS=\"ABCDE-12345\" " +
                "NCERROR=\"55555555\" " +
                "CN=\"Johann Voldemar Jannsen\" " +
                "NCERRORCN=\"0\" " +
                "amount=\"X10\" " +
                "NCERRORCVC=\"1\"/>";
    }
}
