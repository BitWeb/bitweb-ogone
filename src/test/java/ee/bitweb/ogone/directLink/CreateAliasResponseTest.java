package ee.bitweb.ogone.directLink;

import ee.bitweb.ogone.exceptions.ShaComposerException;
import ee.bitweb.ogone.exceptions.XmlParseException;
import ee.bitweb.ogone.shaComposer.MockShaComposer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class CreateAliasResponseTest {

    @Test
    public void testGetParametersFromXmlResponse() {

        try {
            CreateAliasResponse response = new CreateAliasResponse(getRequestXmlResponse());
            Assert.assertEquals("ABCDE-12345", response.getParameter("ALIAS"));
            Assert.assertEquals("Johann Voldemar Jannsen", response.getParameter("CN"));
            Assert.assertEquals("ORDER-1", response.getParameter("orderId"));
            Assert.assertEquals("0", response.getParameter("NCERROR"));
            Assert.assertEquals("0", response.getParameter("STATUS"));
        } catch (XmlParseException e) {
        }
    }

    @Test
    public void testSuccess() {
        try {
            CreateAliasResponse response = new CreateAliasResponse(getRequestXmlResponse());
            Assert.assertTrue(response.isSuccessful());

            CreateAliasResponse responseNotOk = new CreateAliasResponse(getErrorRequestXmlResponse());
            Assert.assertFalse(responseNotOk.isSuccessful());

        } catch (XmlParseException e) {
        }
    }

    @Test
    public void testGetAlias() {
        try {
            CreateAliasResponse response = new CreateAliasResponse(getRequestXmlResponse());
            Alias alias = response.getAlias();
            Assert.assertEquals("ABCDE-12345", alias.toString());

        } catch (XmlParseException e) {
        }
    }

    @Test
    public void testErrors() {
        try {

            CreateAliasResponse response = new CreateAliasResponse(getRequestXmlResponse());
            Assert.assertEquals(0, response.getErrors().size());

            CreateAliasResponse errResponse = new CreateAliasResponse(getErrorRequestXmlResponse());
            Assert.assertEquals(4, errResponse.getErrors().size());

        } catch (XmlParseException e) {
        }
    }

    @Test
    public void testToString() {
        try {
            CreateAliasResponse response = new CreateAliasResponse("<?xml version=\"1.0\" encoding=\"UTF-8\"?><ncresponse STATUS=\"0\" />");
            Assert.assertEquals("{STATUS=0}", response.toString());
        } catch (XmlParseException e) {
        }
    }

    @Test
    public void testToMap() {
        try {
            CreateAliasResponse response = new CreateAliasResponse("<?xml version=\"1.0\" encoding=\"UTF-8\"?><ncresponse STATUS=\"0\" />");
            Map map = response.toMap();
            Assert.assertEquals(map.get("STATUS"), "0");
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
