package ee.bitweb.ogone;

import ee.bitweb.ogone.exceptions.XmlParseException;
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
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractResponse implements Response {

    protected ArrayList<String> ogoneFields = new ArrayList<String>(Arrays.asList(
        "AAVADDRESS", "AAVCHECK", "AAVZIP", "ACCEPTANCE", "ALIAS", "amount", "BIN", "BRAND", "CARDNO", "CCCTY", "CN",
        "COMPLUS", "CREATION_STATUS", "currency", "CVC", "CVCCHECK", "DCC_COMMPERCENTAGE", "DCC_CONVAMOUNT", "DCC_CONVCCY", "DCC_EXCHRATE", "DCC_EXCHRATESOURCE",
        "DCC_EXCHRATETS", "DCC_INDICATOR", "DCC_MARGINPERCENTAGE", "DCC_VALIDHOURS", "DIGESTCARDNO", "ECI", "ED", "ENCCARDNO", "IP", "IPCTY",
        "NBREMAILUSAGE", "NBRIPUSAGE", "NBRIPUSAGE_ALLTX", "NBRUSAGE", "NCERROR", "NCERRORCN", "NCERRORCARDNO", "NCERRORCVC", "NCERRORED", "orderID",
        "PAYID", "PAYIDSUB", "PM", "SCO_CATEGORY", "SCORING", "STATUS", "SUBSCRIPTION_ID", "TRXDATE", "VC", "NCERRORPLUS"
    ));

    protected Map<String, Object> parameters;

    public AbstractResponse(String xmlString) throws XmlParseException {
        parameters = getResponseParameters(xmlString);
    }

    public Object getParameter(String key) {
        return parameters.get(key);
    }

    public String toString() {
        return parameters.toString();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    private Map<String, Object> getResponseParameters(String xmlString) throws XmlParseException{

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            db = documentBuilderFactory.newDocumentBuilder();
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new StringReader(xmlString));

            try {
                Document document = db.parse(inputSource);
                Node firstChild = document.getFirstChild();
                NamedNodeMap attributes = firstChild.getAttributes();
                for (int i = 0; i < attributes.getLength(); i++) {
                    Attr attr = (Attr) attributes.item(i);
                    result.put(attr.getName(), attr.getValue());
                }
            } catch (SAXException e) {
                throw new XmlParseException(e.getMessage(), e);
            } catch (IOException e) {
                throw new XmlParseException(e.getMessage(), e);
            }
        } catch (ParserConfigurationException e1) {
            throw new XmlParseException(e1.getMessage(), e1);
        }

        return result;
    }
}
