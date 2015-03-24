package ee.bitweb.ogone.directLink;

import ee.bitweb.ogone.AbstractDirectLinkRequest;
import ee.bitweb.ogone.exceptions.RequiredFieldException;
import ee.bitweb.ogone.shaComposer.ShaComposer;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class DirectLinkQueryRequest extends AbstractDirectLinkRequest {

    public static final String TEST = "https://secure.ogone.com/ncol/test/querydirect.asp";
    public static final String PRODUCTION = "https://secure.ogone.com/ncol/prod/querydirect.asp";

    public DirectLinkQueryRequest() {
        this.ogoneUri = TEST;
    }

    public DirectLinkQueryRequest(ShaComposer shaComposer) {
        this.shaComposer = shaComposer;
        this.ogoneUri = TEST;
    }

    public void setPayIdSub(String payIdSub) {
        setParameter("payidsub", payIdSub);
    }

    public List<String> getRequiredFields() {
        return Arrays.asList("pspid", "userid", "pswd");
    }

    public List getValidOgoneUris() {
        return  Arrays.asList(TEST, PRODUCTION);
    }

    public boolean validate() throws RequiredFieldException {
        super.validate();

        boolean empty = true;
        for (List<String> group : getRequiredFieldGroups()) {
            for (String el : group) {
                if (parameters.containsKey(el) && parameters.get(el) != null && !((String) parameters.get(el)).isEmpty()) {
                    empty = false;
                    break;
                }
            }
            if (empty) {
                throw new RequiredFieldException("At least one of these fields should not be empty: " +  StringUtils.join(group, ", "));
            }
        }

        return true;
    }
}
