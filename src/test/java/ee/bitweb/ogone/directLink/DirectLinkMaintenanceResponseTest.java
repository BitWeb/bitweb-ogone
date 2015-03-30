package ee.bitweb.ogone.directLink;

import ee.bitweb.ogone.exceptions.XmlParseException;
import ee.bitweb.ogone.shaComposer.MockShaComposer;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by priit on 30.03.15.
 */
public class DirectLinkMaintenanceResponseTest {


    @Test
    public void testConstructResponse() {

        try{
            DirectLinkMaintenanceResponse response = new DirectLinkMaintenanceResponse( getRequestXmlResponse() );
        } catch (XmlParseException e) {
        }
    }

    @Test
    public void testSuccess() {
        try {
            DirectLinkMaintenanceResponse response = new DirectLinkMaintenanceResponse( getRequestXmlResponse() );
            Assert.assertTrue(response.isSuccessful());
            CreateAliasResponse responseNotOk = new CreateAliasResponse(getErrorRequestXmlResponse());
            Assert.assertFalse(responseNotOk.isSuccessful());

        } catch (XmlParseException e) {
        }
    }

    @Test
    public void testFilterRequestParameters() {
        try {
            DirectLinkMaintenanceResponse response = new DirectLinkMaintenanceResponse( getRequestXmlResponse() );

            Map<String, Object> requets = new HashMap<String, Object>();
            requets.put("NCERRORPLUS", "CUSTOM_1");
            requets.put("PAYIDSUB", "CUSTOM_2");
            requets.put("AAVADDRESS", "OGONE");
            requets.put("NOT_OGONE", "NO RETURN");

            Map<String, Object> result = response.filterRequestParameters( requets );

            Assert.assertEquals("CUSTOM_1", result.get("NCERRORPLUS") );
            Assert.assertEquals("CUSTOM_2", result.get("PAYIDSUB") );
            Assert.assertEquals("OGONE", result.get("AAVADDRESS") );
            Assert.assertEquals(null, result.get("NOT_OGONE") );
        } catch (XmlParseException e) {

        }
    }

    private String getRequestXmlResponse() {

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<ncresponse STATUS=\"0\" " +
                "orderId=\"ORDER-1\" " +
                "ED=\"0616\" NCERRORED=\"0\" " +
                "CVC=\"XXX\" " +
                "CARDNO=\"XXXXXXXXXXXX1111\" " +
                "NCERRORCARDNO=\"0\" " +
                "ALIAS=\"ABCDE-12345\" " +
                "NCERROR=\"0\" " +
                "CN=\"Johann Voldemar Jannsen\" " +
                "NCERRORCN=\"0\" " +
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
                "NCERRORCVC=\"1\"/>";
    }
}
