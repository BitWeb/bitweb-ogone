package com.github.bitweb.ogone.parameterFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class ShaOutParameterFilter implements ParameterFilter {

    private ArrayList<String> allowed = new ArrayList<String>(Arrays.asList(
        "AAVADDRESS", "AAVCHECK", "AAVZIP", "ACCEPTANCE", "ALIAS", "AMOUNT", "BRAND",
        "CARDNO", "CCCTY", "CN", "COMPLUS", "CREATION_STATUS", "CURRENCY", "CVC", "CVCCHECK",
        "DCC_COMMPERCENTAGE", "DCC_CONVAMOUNT", "DCC_CONVCCY", "DCC_EXCHRATE", "DCC_EXCHRATESOURCE",
        "DCC_EXCHRATETS", "DCC_INDICATOR", "DCC_MARGINPERCENTAGE", "DCC_VALIDHOURS",
        "DIGESTCARDNO", "ECI", "ED", "ENCCARDNO", "IP", "IPCTY", "NBREMAILUSAGE",
        "NBRIPUSAGE", "NBRIPUSAGE_ALLTX", "NBRUSAGE", "NCERROR", "NCERRORCN", "NCERRORCARDNO", "NCERRORCVC",
        "NCERRORED","ORDERID", "PAYID", "PM", "SCO_CATEGORY", "SCORING", "STATUS", "SUBSCRIPTION_ID",
        "TRXDATE", "VC"
    ));

    public Map<String, Object> filter(Map<String, Object> parameters) {
        ParameterFilter parameterFilter = new GeneralParameterFilter();
        Map<String, Object> filteredParameters = parameterFilter.filter(parameters);
        filteredParameters.keySet().retainAll(allowed);
        return filteredParameters;
    }

}
