package com.github.bitweb.ogone;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

abstract public class AbstractResponse implements Response {

    protected ArrayList<String> ogoneFields = new ArrayList<String>(Arrays.asList(
        "AAVADDRESS", "AAVCHECK", "AAVZIP", "ACCEPTANCE", "ALIAS", "amount", "BIN", "BRAND", "CARDNO", "CCCTY", "CN",
        "COMPLUS", "CREATION_STATUS", "currency", "CVC", "CVCCHECK", "DCC_COMMPERCENTAGE", "DCC_CONVAMOUNT", "DCC_CONVCCY", "DCC_EXCHRATE", "DCC_EXCHRATESOURCE",
        "DCC_EXCHRATETS", "DCC_INDICATOR", "DCC_MARGINPERCENTAGE", "DCC_VALIDHOURS", "DIGESTCARDNO", "ECI", "ED", "ENCCARDNO", "IP", "IPCTY",
        "NBREMAILUSAGE", "NBRIPUSAGE", "NBRIPUSAGE_ALLTX", "NBRUSAGE", "NCERROR", "NCERRORCN", "NCERRORCARDNO", "NCERRORCVC", "NCERRORED", "orderID",
        "PAYID", "PAYIDSUB", "PM", "SCO_CATEGORY", "SCORING", "STATUS", "SUBSCRIPTION_ID", "TRXDATE", "VC", "NCERRORPLUS"
    ));

    protected Map<String, Object> parameters;

    protected String shaSign;

    public AbstractResponse() {
    }

    public AbstractResponse(String xmlString) {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;

        try {
            db = documentBuilderFactory.newDocumentBuilder();
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new StringReader(xmlString));

            try {
                Document document = db.parse(inputSource);
//                Element root = document.getDocumentElement();
                Node firstChild = document.getFirstChild();
                NamedNodeMap attributes = firstChild.getAttributes();
                for (int i = 0; i < attributes.getLength(); i++) {
                    Attr attr = (Attr) attributes.item(i);
                    // attr.getName() + " " + attr.getValue()
                }
            } catch (SAXException e) {
                // handle SAXException
            } catch (IOException e) {
                // handle IOException
            }
        } catch (ParserConfigurationException e1) {
        }

    }

    public String extractShaSign(Map<String, Object> parameters) {
        if (!(parameters.containsKey("SHASIGN") || parameters.get("SHASIGN").toString().isEmpty())) {
            throw new IllegalArgumentException("SHASIGN parameter not present in parameters.");
        }
        return ((String) parameters.get("SHASIGN"));
    }

    public Object getParameter(String key) {
        return parameters.get(key);
    }

    protected Map<String, Object> filterRequestParameters(Map<String, Object> requestParameters) {
        requestParameters.keySet().retainAll(new ArrayList<String>(ogoneFields));
        return requestParameters;
    }

    public String toString() {
        return parameters.toString();
    }

}
