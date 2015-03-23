package ee.bitweb.ogone;

import ee.bitweb.ogone.directLink.Alias;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract public class AbstractDirectLinkRequest extends AbstractPaymentRequest {

    public void setUserId(String userId) {
        setParameter("userid", userId);
    }

    public void setPassword(String password) {
        setPswd(password);
    }

    public void setPswd(String password) {
        if(password.length() < 8) {
            throw new IllegalArgumentException("Password is too short");
        }
        setParameter("pswd", password);
    }

    public void setPayId(String payId) {
        setParameter("payid", payId);
    }

    public String getPayId() {
        return (String) parameters.get("payid");
    }

    public void setOrderId(String orderId) {
        setParameter("orderid", orderId);
    }

    protected ArrayList<List<String>> getRequiredFieldGroups() {
        ArrayList<List<String>> fieldGroups = new ArrayList<List<String>>();
        fieldGroups.add(Arrays.asList("payid", "orderid"));
        return fieldGroups;
    }

    public void setAlias(Alias alias) {
        setParameter("alias", alias.toString());
    }

}
