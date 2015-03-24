package ee.bitweb.ogone;

import ee.bitweb.ogone.exceptions.RequiredFieldException;
import ee.bitweb.ogone.exceptions.ShaComposerException;
import ee.bitweb.ogone.shaComposer.ShaComposer;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractRequest implements Request {

    protected String ogoneUri;
    protected Map<String, Object> parameters = new TreeMap<String, Object>();
    protected String response;
    protected ShaComposer shaComposer;

    public ArrayList<String> allowedLanguages = new ArrayList<String>(Arrays.asList(
        "en_US","cs_CZ", "de_DE",
        "dk_DK","el_GR","es_ES",
        "fr_FR","it_IT","ja_JP",
        "nl_BE","nl_NL","no_NO",
        "pl_PL","pt_PT","ru_RU",
        "se_SE","sk_SK","tr_TR"
    ));

    protected ArrayList<String> ogoneFields = new ArrayList<String>(Arrays.asList(
        "pspid", "orderid", "com", "amount", "currency", "language", "cn", "email",
        "cardno", "cvc", "ed", "ownerzip", "owneraddress", "ownercty", "ownertown",
        "ownertelno", "accepturl", "declineurl", "exceptionurl", "cancelurl", "backurl",
        "complus", "paramplus", "pm", "brand", "title", "bgcolor", "txtcolor", "tblbgcolor",
        "tbltxtcolor", "buttonbgcolor", "buttontxtcolor", "logo", "fonttype", "tp", "paramvar"
    ));

    public void setShaComposer(ShaComposer shaComposer) {
        this.shaComposer = shaComposer;
    }

    public String getOgoneUri() {
        return ogoneUri;
    }

    public String getShaSign() throws ShaComposerException {
        return shaComposer.compose(parameters);
    }

    public void setPspid(String pspid) {
        if (pspid.length() > 30) {
            throw  new IllegalArgumentException("PSPID is too long");
        }
        this.parameters.put("pspid", pspid);
    }

    public void setOgoneUri(String ogoneUri) {
        validateOgoneUri(ogoneUri);
        this.ogoneUri = ogoneUri;
    }

    public void setCustomerName(String customerName) {
        setCn(customerName);
    }

    public void setCn(String customerName) {
        setParameter("cn", customerName.replaceAll("[\"']", ""));
    }

//    public void setAcceptUrl(String acceptUrl) {
////      validateUri(acceptUrl)
//        setParameter("accepturl", acceptUrl);
//    }
//
//    public void setDeclineUrl(String declineUrl) {
//        validateUri(declineUrl);
//        setParameter("declineurl", declineUrl);
//    }
//
//    public void setExceptionUrl(String exceptionUrl) {
//        validateUri(exceptionUrl);
//        setParameter("exceptionurl", exceptionUrl);
//    }
//
//    public void setCancelUrl(String cancelUrl) {
//        validateUri(cancelUrl);
//        setParameter("cancelurl", cancelUrl);
//    }
//
//    public void setBackUrl(String backUrl) {
//        validateUri(backUrl);
//        setParameter("backurl", backUrl);
//    }

    public void setParameter(String parameter, Object value) {
        this.parameters.put(parameter, value);
    }

    protected void validateUri(String uri) {
        if (uri.length() > 200) {
            throw new IllegalArgumentException("Uri is too long");
        }
        String[] schemes = {"http", "https"};

        UrlValidator urlValidator = new UrlValidator(schemes, UrlValidator.NO_FRAGMENTS);
        if (!urlValidator.isValid(uri)) {
            throw new IllegalArgumentException("Uri (" + uri + ") is not valid");
        }
    }

    protected void validateOgoneUri(String ogoneUri) {
        validateUri(ogoneUri);
        if (!getValidOgoneUris().contains(ogoneUri)) {
            throw new IllegalArgumentException("No valid Ogone url");
        }
    }

    public boolean validate() throws RequiredFieldException {
        for (String field : getRequiredFields()) {
            if (!parameters.containsKey(field)) {
                throw new RequiredFieldException("Field " + field + " value cannot be empty");
            }
        }
        return true;
    }

    public Map toArray() throws RequiredFieldException {
        validate();

        Map<String, Object> result = new TreeMap<String, Object>();
        for (Map.Entry entry : parameters.entrySet()) {
            result.put(entry.getKey().toString().toUpperCase(), entry.getValue());
        }

        return result;
    }
    
}
