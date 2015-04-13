package ee.bitweb.ogone.directLink;

import ee.bitweb.ogone.shaComposer.MockShaComposer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by priit on 30.03.15.
 */
public class DirectLinkMaintenanceRequestTest {


    @Test
    public void testConstructRequest() {
        DirectLinkMaintenanceRequest request = createRequest();
        DirectLinkMaintenanceRequest requestWithSha = new DirectLinkMaintenanceRequest( new MockShaComposer("hashhash"));
    }

    @Test
    public void testHasUris() {
        DirectLinkMaintenanceRequest request = new DirectLinkMaintenanceRequest();
        Assert.assertEquals(2, request.getValidOgoneUris().size());
    }

    @Test
    public void testGetRequestFields() {
        DirectLinkMaintenanceRequest request = new DirectLinkMaintenanceRequest();
        Assert.assertEquals(4, request.getRequiredFields().size());
    }

    @Test
    public void testSetAmount() {
        DirectLinkMaintenanceRequest request = createRequest();
        request.setAmount(5);
    }

    @Test
    public void testSetOperationOk() {
        DirectLinkMaintenanceRequest request = createRequest();
        request.setOperation(DirectLinkMaintenanceRequest.OPERATION_AUTHORISATION_DELETE);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetOperationNotOk() {
        DirectLinkMaintenanceRequest request = createRequest();
        request.setOperation("SOMETHING_INVALID");
    }

    private DirectLinkMaintenanceRequest createRequest(){

        DirectLinkMaintenanceRequest request = new DirectLinkMaintenanceRequest();
        request.setParameter("pspid",123456789);
        request.setParameter("userid","1");
        request.setParameter("operation","1");
        request.setPassword("OK_PASSWORD");
        return request;
    }
}
