package com.github.bitweb.ogone.directLink;

import com.github.bitweb.ogone.AbstractRequest;
import com.github.bitweb.ogone.shaComposer.ShaComposer;

import java.util.Arrays;
import java.util.List;

public class DirectLinkMaintenanceRequest extends AbstractRequest {

    public static final String TEST = "https://secure.ogone.com/ncol/test/maintenancedirect.asp";
    public static final String PRODUCTION = "https://secure.ogone.com/ncol/prod/maintenancedirect.asp";
    public static final String OPERATION_AUTHORISATION_RENEW = "REN";
    public static final String OPERATION_AUTHORISATION_DELETE = "DEL";
    public static final String OPERATION_AUTHORISATION_DELETE_AND_CLOSE = "DES";
    public static final String OPERATION_CAPTURE_PARTIAL = "SAL";
    public static final String OPERATION_CAPTURE_LAST_OR_FULL = "SAS";
    public static final String OPERATION_REFUND_PARTIAL = "RFD";
    public static final String OPERATION_REFUND_LAST_OR_FULL = "RFS";

    public DirectLinkMaintenanceRequest() {
        this.ogoneUri = TEST;
    }

    public DirectLinkMaintenanceRequest(ShaComposer shaComposer) {
        this();
        this.shaComposer = shaComposer;
    }

    public List<String> getRequiredFields() {
        return Arrays.asList("pspid", "userid", "pswd", "operation");
    }

    public List<String> getValidOgoneUris() {
        return Arrays.asList(TEST, PRODUCTION);
    }

    public void setAmount(Integer amount) {
        setParameter("amount", amount);
    }

    public void setOperation(String operation) {
        if (!getValidOperations().contains(operation)) {
            throw new IllegalArgumentException("Invalid operation");
        }
        parameters.put("operation", operation);
    }

    private List<String> getValidOperations() {
        return Arrays.asList(
            OPERATION_AUTHORISATION_RENEW,
            OPERATION_AUTHORISATION_DELETE,
            OPERATION_AUTHORISATION_DELETE_AND_CLOSE,
            OPERATION_CAPTURE_PARTIAL,
            OPERATION_CAPTURE_LAST_OR_FULL,
            OPERATION_REFUND_PARTIAL,
            OPERATION_REFUND_LAST_OR_FULL
        );
    }
}
