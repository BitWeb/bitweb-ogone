package ee.bitweb.ogone.directLink;

import ee.bitweb.ogone.Passphrase;
import ee.bitweb.ogone.exceptions.RequiredFieldException;
import ee.bitweb.ogone.exceptions.ShaComposerException;
import ee.bitweb.ogone.shaComposer.AllParametersShaComposer;
import ee.bitweb.ogone.shaComposer.MockShaComposer;
import ee.bitweb.ogone.shaComposer.ShaComposer;
import org.junit.Assert;
import org.junit.Test;

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

}
