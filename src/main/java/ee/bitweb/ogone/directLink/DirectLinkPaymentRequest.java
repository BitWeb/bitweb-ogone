package ee.bitweb.ogone.directLink;

import ee.bitweb.ogone.AbstractDirectLinkRequest;
import ee.bitweb.ogone.shaComposer.ShaComposer;

import java.util.Arrays;
import java.util.List;

public class DirectLinkPaymentRequest extends AbstractDirectLinkRequest {

    public static final String TEST = "https://secure.ogone.com/ncol/test/orderdirect.asp";
    public static final String PRODUCTION = "https://secure.ogone.com/ncol/prod/orderdirect.asp";

    public DirectLinkPaymentRequest() {
        this.ogoneUri = TEST;
    }

    public DirectLinkPaymentRequest(ShaComposer shaComposer) {
        this();
        this.shaComposer = shaComposer;
    }

    public List<String> getRequiredFields() {
        return Arrays.asList("pspid", "currency", "amount", "orderid", "userid", "pswd");
    }

    public List<String> getValidOgoneUris() {
        return Arrays.asList(TEST, PRODUCTION);
    }

    public void setUserId(String userId) {
        if (userId.length() < 8) {
            throw new IllegalArgumentException("User ID is too short");
        }
        setParameter("userid", userId);
    }

    public void setPassword(String password) {
        setPswd(password);
    }

    public void setEci(Eci eci) {
        setParameter("eci", eci.toString());
    }

}
