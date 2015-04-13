package ee.bitweb.ogone.directLink;

import ee.bitweb.ogone.Passphrase;
import ee.bitweb.ogone.exceptions.RequiredFieldException;
import ee.bitweb.ogone.exceptions.RequiredFieldExceptionTest;
import ee.bitweb.ogone.exceptions.ShaComposerException;
import ee.bitweb.ogone.shaComposer.AllParametersShaComposer;
import ee.bitweb.ogone.shaComposer.MockShaComposer;
import ee.bitweb.ogone.shaComposer.ShaComposer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class CreateAliasRequestTest {

    @Test
    public void testShaSign() {
        String shaSign = "MOCKSHASIGN";
        try {
            CreateAliasRequest request = new CreateAliasRequest(new MockShaComposer(shaSign));
            Assert.assertEquals(shaSign, request.getShaSign());
        } catch (ShaComposerException e) {
        }
    }

    @Test
    public void testRequiredFields() {
        CreateAliasRequest request = createRequest();
        try {
            Assert.assertTrue(request.validate());
        } catch (RequiredFieldException e) {
        }
    }

    @Test(expected = RequiredFieldException.class)
    public void testRequiredFieldMissing() throws RequiredFieldException {
        CreateAliasRequest request = createRequestWithMinimalFields();
        request.validate();
    }

    private CreateAliasRequest createRequest() {
        Passphrase passphrase = new Passphrase("Mysecretsig1875!?");
        ShaComposer shaComposer = new AllParametersShaComposer(passphrase);
        CreateAliasRequest createAliasRequest = new CreateAliasRequest(shaComposer);
        createAliasRequest.setParameter("accepturl", "");
        createAliasRequest.setParameter("exceptionurl", "");
        createAliasRequest.setParameter("pspid", "123456789");
        createAliasRequest.setParameter("orderid", "987654321");
        createAliasRequest.setOgoneUri(CreateAliasRequest.TEST);
        return createAliasRequest;
    }

    private CreateAliasRequest createRequestWithMinimalFields() {
        Passphrase passphrase = new Passphrase("Mysecretsig1875!?");
        ShaComposer shaComposer = new AllParametersShaComposer(passphrase);
        CreateAliasRequest createAliasRequest = new CreateAliasRequest(shaComposer);
        createAliasRequest.setParameter("orderid", "987654321");
        createAliasRequest.setOgoneUri(CreateAliasRequest.TEST);
        return createAliasRequest;
    }

    @Test
    public void testSetAlias() {

        String aliastext = "ALIASTEXT";
        Alias aliasValue = new Alias(aliastext);

        CreateAliasRequest request = createRequest();
        request.setAlias( aliasValue );
        try {
            Map map = request.toArray();
            Assert.assertTrue(aliastext.equals(map.get("ALIAS").toString()));
        } catch (RequiredFieldException e) {
        }
    }

    @Test
    public void testSetShaComposer() {
        CreateAliasRequest request = createRequest();
        request.setShaComposer(new MockShaComposer("SHA"));
    }

    @Test
    public void testGetOgoneUri() {
        CreateAliasRequest request = createRequest();
        String uri = request.getOgoneUri();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPspidNotOk() {
        CreateAliasRequest request = createRequest();
        request.setPspid("INVALID_VALUE: Lorem ipsum dolor sit amet, instructior concludaturque eam no, in eius tritani cum. Pro laboramus constituam voluptatibus et, nibh interesset cu pri. Ei mel iudicabit disputationi, ad est oblique torquatos constituto. Aliquip definiebas definitionem no vel.");
    }

    @Test
    public void testSetPspidOk() {
        CreateAliasRequest request = createRequest();
        request.setPspid("VALUE");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetOgoneUriNotOk() {
        CreateAliasRequest request = createRequest();
        request.setOgoneUri("INVALID URI");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidOgoneUri() {
        CreateAliasRequest request = createRequest();
        request.setOgoneUri("http://test.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetOgoneUriNotOkTooLong() {
        CreateAliasRequest request = createRequest();
        request.setOgoneUri("INVALID_VALUE: Lorem ipsum dolor sit amet, instructior concludaturque eam no, in eius tritani cum. Pro laboramus constituam voluptatibus et, nibh interesset cu pri. Ei mel iudicabit disputationi, ad est oblique torquatos constituto. Aliquip definiebas definitionem no vel. INVALID_VALUE: Lorem ipsum dolor sit amet, instructior concludaturque eam no, in eius tritani cum. Pro laboramus constituam voluptatibus et, nibh interesset cu pri. Ei mel iudicabit disputationi, ad est oblique torquatos constituto. Aliquip definiebas definitionem no vel.");
    }

    @Test
    public void testSetOgoneUriOk() {
        CreateAliasRequest request = createRequest();
        request.setOgoneUri(CreateAliasRequest.TEST);
    }

    @Test
    public void testSetCustomerName() {
        CreateAliasRequest request = createRequest();
        request.setCustomerName("Na\"me");

        try {
            Assert.assertEquals("Name", request.toArray().get("CN"));
        } catch (RequiredFieldException e){

        }
    }


}
