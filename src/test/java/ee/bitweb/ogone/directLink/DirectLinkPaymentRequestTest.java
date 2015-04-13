package ee.bitweb.ogone.directLink;

import ee.bitweb.ogone.shaComposer.MockShaComposer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by priit on 30.03.15.
 */
public class DirectLinkPaymentRequestTest {

    @Test
    public void testConstructRequest() {
        DirectLinkPaymentRequest request = createRequest();
        DirectLinkPaymentRequest requestWithSha = new DirectLinkPaymentRequest( new MockShaComposer("hashhash"));
    }

    @Test
    public void testGetRequestFields() {
        DirectLinkPaymentRequest request = new DirectLinkPaymentRequest();
        Assert.assertEquals(6, request.getRequiredFields().size());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetUserIdNotOk() {
        DirectLinkPaymentRequest request = createRequest();
        request.setUserId("SHORT");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetPasswordNotOk() {
        DirectLinkPaymentRequest request = createRequest();
        request.setPassword("SHORT");
    }

    @Test
    public void testHasUris() {
        DirectLinkPaymentRequest request = new DirectLinkPaymentRequest();
        Assert.assertEquals(2, request.getValidOgoneUris().size());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetOrderIdTooLong() {
        DirectLinkPaymentRequest request = createRequest();
        request.setOrderId("1234567890123456789012345678901");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetOrderIdInvalidChar() {
        DirectLinkPaymentRequest request = createRequest();
        request.setOrderId("qwerty\'");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetInvalidCom() {
        DirectLinkPaymentRequest request = createRequest();
        request.setCom("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetInvalidAmountLow() {
        DirectLinkPaymentRequest request = createRequest();
        request.setAmount(-1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetInvalidEmail() {
        DirectLinkPaymentRequest request = createRequest();
        request.setEmail("testtesttesttesttesttesttesttesttesttesttesttesttest@test.com");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetInvalidOwnedAddress() {
        DirectLinkPaymentRequest request = createRequest();
        request.setOwnedAddress("testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttest");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetInvalidAmount() {
        DirectLinkPaymentRequest request = createRequest();
        request.setAmount(2147483647);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetInvalidOwnerZip() {
        DirectLinkPaymentRequest request = createRequest();
        request.setOwnerZip("12345678901");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetInvalidOwnerCty() {
        DirectLinkPaymentRequest request = createRequest();
        request.setOwnerCty("USD");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetInvalidOwnerTelNo() {
        DirectLinkPaymentRequest request = createRequest();
        request.setOwnerTelNo("1234567890123456789012345678901");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetInvalidBrand() {
        DirectLinkPaymentRequest request = createRequest();
        request.setBrand("RANDOM");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetInvalidPm() {
        DirectLinkPaymentRequest request = createRequest();
        request.setPm("RANDOM");
    }

    private DirectLinkPaymentRequest createRequest(){

        DirectLinkPaymentRequest request = new DirectLinkPaymentRequest();
        request.setPayId("X");
        request.setOrderId("X");
        request.setPassword("LONG_PASSWORD");
        request.setAlias(new Alias("ALIAS"));
        request.setUserId("LONG_USER_ID");
        request.setEci(new Eci(0));
        request.setOrderDescription("Descr");
        request.setAmount(12);

        request.setEmail("value");
        request.setOwnedAddress("value");
        request.setOwnerZip("value");
        request.setOwnerTown("value");
        request.setOwnerCountry("ET");
        request.setOwnerPhone("value");
        request.setFeedbackMessage("value");
        request.setBrand("AIRPLUS");
        request.setDynamicTemplateUri("http://test.com");
        request.getPayId();

        return request;
    }
}
