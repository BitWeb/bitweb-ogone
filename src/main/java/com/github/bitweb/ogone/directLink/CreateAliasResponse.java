package com.github.bitweb.ogone.directLink;

import com.github.bitweb.ogone.AbstractResponse;
import com.github.bitweb.ogone.exceptions.ShaComposerException;
import com.github.bitweb.ogone.shaComposer.ShaComposer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CreateAliasResponse extends AbstractResponse {

    public static final int STATUS_OK = 0;
    public static final int STATUS_NOK = 1;
    public static final int STATUS_UPDATED = 2;
    public static final String NCERROR_INCORRECT_ORDERID = "555554";
    public static final String NCERROR_GENERAL_ERROR = "55555555";
    public static final String NCERROR_SHAIN_MISMATCH = "50001184";
    public static final String NCERROR_ALIAS_EXISTS_FOR_ORDERID = "50001186";
    public static final String NCERROR_ALIAS_EXISTS = "50001187";
    public static final String NCERROR_WRONG_BRAND = "50001300";
    public static final String NCERROR_WRONG_BANK_ACCOUNT_FORMAT = "50001301";
    public static final String NCERRORCN_NAME_MISSING = "60001057";
    public static final String NCERRORCN_NAME_TOO_LONG = "50001174";
    public static final String NCERRORCARDNO_INVALID_CARD_NUMBER = "30141001";
    public static final String NCERRORCARDNO_BRAND_CARD_NUMBER_MISMATCH = "50001069";
    public static final String NCERRORCARDNO_CARD_NUMBER_TOO_LONG = "50001176";
    public static final String NCERRORCARDNO_CARD_NUMBER_NON_NUMERIC = "50001177";
    public static final String NCERRORCARDNO_CARD_NUMBER_EMPTY = "50001178";
    public static final String NCERRORCVC_EMPTY = "50001090";
    public static final String NCERRORCVC_TOO_LONG = "50001179";
    public static final String NCERRORCVC_NON_NUMERIC = "50001180";
    public static final String NCERRORED_NON_NUMERIC = "50001181";
    public static final String NCERRORED_INVALID_MONTH = "50001182";
    public static final String NCERRORED_IN_THE_PAST = "50001183";
    public static final String NCERRORED_EMPTY_OR_WRONG_FORMAT = "31061001";

    public HashMap<String, String> errorCodeDescriptions;

    {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(NCERROR_INCORRECT_ORDERID, "Incorrect ORDERID (after resubmission)");
        map.put(NCERROR_GENERAL_ERROR, "General error");
        map.put(NCERROR_SHAIN_MISMATCH, "SHA-IN mismatch");
        map.put(NCERROR_ALIAS_EXISTS_FOR_ORDERID, "Operation not allowed (when an ORDERID is sent for which an alias already exists)");
        map.put(NCERROR_ALIAS_EXISTS, "Operation not allowed (when an alias is sent that already exists)");
        map.put(NCERROR_WRONG_BRAND, "Wrong brand specified (Direct Debits)");
        map.put(NCERROR_WRONG_BANK_ACCOUNT_FORMAT, "Wrong bank account format (Direct Debits)");
        map.put(NCERRORCN_NAME_MISSING, "Name is missing");
        map.put(NCERRORCN_NAME_TOO_LONG, "Name is too long");
        map.put(NCERRORCARDNO_INVALID_CARD_NUMBER, "nvalid card number");
        map.put(NCERRORCARDNO_BRAND_CARD_NUMBER_MISMATCH, "Brand and card number do not match");
        map.put(NCERRORCARDNO_CARD_NUMBER_TOO_LONG, "Card number is too long");
        map.put(NCERRORCARDNO_CARD_NUMBER_NON_NUMERIC, "Card number contains non-numeric info");
        map.put(NCERRORCARDNO_CARD_NUMBER_EMPTY, "Card number too short/empty");
        map.put(NCERRORCVC_EMPTY, "CVC missing or too short");
        map.put(NCERRORCVC_TOO_LONG, "CVC too long");
        map.put(NCERRORCVC_NON_NUMERIC, "CVC contains non-numeric information");
        map.put(NCERRORED_NON_NUMERIC, "Expiry date contains non-numeric information");
        map.put(NCERRORED_INVALID_MONTH, "Invalid expiry month");
        map.put(NCERRORED_IN_THE_PAST, "Expiry date must be in the future");
        map.put(NCERRORED_EMPTY_OR_WRONG_FORMAT, "Expiry date empty or wrong format");
        this.errorCodeDescriptions = map;
    }

    public CreateAliasResponse(String xmlResponse) {
        super(xmlResponse);
    }


    public boolean isValid(ShaComposer shaComposer) throws ShaComposerException {
        return shaComposer.compose(parameters).equals(shaSign);
    }

    public boolean isSuccessful() {
        return Arrays.asList(STATUS_OK, STATUS_UPDATED).contains(Integer.valueOf((String) getParameter("STATUS")));
    }

    public Alias getAlias() {
        return new Alias((String)parameters.get("ALIAS"), (String)parameters.get("CN"), (String)parameters.get("CARDNO"), (String)parameters.get("ED"));
    }

    public List<String> getErrors() {
        List<String> errors = new ArrayList<String>();
        if (isSuccessful()) {
            return errors;
        }
        if (!getParameter("NCERROR").equals("0")) {
            addError(errors, (String) getParameter("NCERROR"), "NCERROR");
        }
        if (!getParameter("NCERRORED").equals("0")) {
            addError(errors, (String) getParameter("NCERROR"), "NCERROR");
        }
        if (!getParameter("NCERRORCARDNO").equals("0")) {
            addError(errors, (String) getParameter("NCERROR"), "NCERROR");
        }
        if (!getParameter("NCERRORCVC").equals("0")) {
            addError(errors, (String) getParameter("NCERROR"), "NCERROR");
        }

        return errors;
    }

    private void addError(List<String> errors, String param, String code) {
        String error = "Unknown error for " + param;
        if (errorCodeDescriptions.containsKey(param)) {
            error = errorCodeDescriptions.get(param);
        }
        errors.add(error);
    }
}
