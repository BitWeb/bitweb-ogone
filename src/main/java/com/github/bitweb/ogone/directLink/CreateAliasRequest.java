package com.github.bitweb.ogone.directLink;

import com.github.bitweb.ogone.AbstractRequest;
import com.github.bitweb.ogone.shaComposer.ShaComposer;

import java.util.Arrays;
import java.util.List;

public class CreateAliasRequest extends AbstractRequest {

    public static final String TEST = "https://secure.ogone.com/ncol/test/alias_gateway_utf8.asp";
    public static final String PRODUCTION = "https://secure.ogone.com/ncol/prod/alias_gateway_utf8.asp";

    public CreateAliasRequest(ShaComposer shaComposer) {
        this.shaComposer = shaComposer;
        this.ogoneUri = TEST;
    }

    public List<String> getRequiredFields() {
        return Arrays.asList("pspid", "accepturl", "exceptionurl");
    }

    public List<String> getValidOgoneUris() {
        return Arrays.asList(TEST, PRODUCTION);
    }

    public void setAlias(Alias alias) {
        setParameter("alias", alias.toString());
//        parameters = parameters + alias.toArray()
    }

}
