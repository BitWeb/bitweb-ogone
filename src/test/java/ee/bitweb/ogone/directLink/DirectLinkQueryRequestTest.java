package ee.bitweb.ogone.directLink;

import ee.bitweb.ogone.exceptions.RequiredFieldException;
import ee.bitweb.ogone.exceptions.RequiredFieldExceptionTest;
import ee.bitweb.ogone.shaComposer.MockShaComposer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by priit on 30.03.15.
 */
public class DirectLinkQueryRequestTest {

    @Test
    public void testConstructRequest() {
        DirectLinkQueryRequest request = createRequest();
        DirectLinkQueryRequest requestWithSha = new DirectLinkQueryRequest( new MockShaComposer("hashhash"));
    }

    @Test
    public void testSetPayIdSub(){
        DirectLinkQueryRequest request = createRequest();
        request.setPayIdSub("Value");
    }

    @Test
    public void testGetRequestFields() {
        DirectLinkQueryRequest request = new DirectLinkQueryRequest();
        Assert.assertEquals(3, request.getRequiredFields().size());
    }

    @Test
    public void testHasUris() {
        DirectLinkQueryRequest request = new DirectLinkQueryRequest();
        Assert.assertEquals(2, request.getValidOgoneUris().size());
    }

    @Test
    public void testSetUserId() {
        DirectLinkQueryRequest request = createRequest();
        request.setUserId("USER_ID");
    }

    @Test
    public void testValidationReqFieldException(){

        DirectLinkQueryRequest request = new DirectLinkQueryRequest();

        Boolean isValid;

        try {
            isValid = request.validate();
        } catch (RequiredFieldException e) {
            isValid = false;
        }
        Assert.assertFalse(isValid);
    }

    @Test
    public void testValidation(){

        DirectLinkQueryRequest request = new DirectLinkQueryRequest();
        request.setParameter("pspid","1");
        request.setParameter("userid","1");
        request.setParameter("orderid","1");
        request.setParameter("pswd",null);

        Boolean isValid;
        try {
            isValid = request.validate();
        } catch (RequiredFieldException e) {
            e.printStackTrace();
            isValid = false;
        }
        Assert.assertTrue( isValid );
    }

    @Test
    public void testGroupsValidation(){

        DirectLinkQueryRequest request = new DirectLinkQueryRequest();
        request.setParameter("pspid","1");
        request.setParameter("userid","1");
        request.setParameter("pswd","1");
        Boolean isValid;
        String message = "";
        try {
            isValid = request.validate();
        } catch (RequiredFieldException e) {
            message = e.getMessage();
            isValid = false;
        }
        Assert.assertFalse(isValid);
        Assert.assertTrue(message.contains("At least one of these fields should not be empty: payid, orderid"));
    }

    private DirectLinkQueryRequest createRequest(){

        DirectLinkQueryRequest request = new DirectLinkQueryRequest();
        request.setParameter("pspid",123456789);
        request.setParameter("userid","1");
        request.setParameter("pswd","1");
        request.setParameter("operation","1");
        return request;
    }

}
