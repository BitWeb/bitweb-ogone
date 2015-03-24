package ee.bitweb.ogone.directLink;

import ee.bitweb.ogone.exceptions.ShaComposerException;
import ee.bitweb.ogone.exceptions.XmlParseException;
import ee.bitweb.ogone.shaComposer.MockShaComposer;
import org.junit.Assert;
import org.junit.Test;

public class CreateAliasResponseTest {

    @Test
    public void testGetParametersFromXmlResponse() {

        try {
            CreateAliasResponse response = new CreateAliasResponse(getRequestXmlRespoonse());
            Assert.assertEquals("ABCDE-12345", response.getParameter("ALIAS"));
            Assert.assertEquals("Johann Voldemar Jannsen", response.getParameter("CN"));
            Assert.assertEquals("ORDER-1", response.getParameter("orderId"));
            Assert.assertEquals("0", response.getParameter("NCERROR"));
            Assert.assertEquals("0", response.getParameter("STATUS"));
        } catch (XmlParseException e) {
        }
    }

    private String getRequestXmlRespoonse() {

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

}
