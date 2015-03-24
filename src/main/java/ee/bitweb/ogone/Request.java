package ee.bitweb.ogone;

import ee.bitweb.ogone.exceptions.RequiredFieldException;
import ee.bitweb.ogone.exceptions.ShaComposerException;

import java.util.List;
import java.util.Map;

public interface Request {

    public Map toArray() throws RequiredFieldException;
    public String getOgoneUri();
    public String getShaSign() throws ShaComposerException;
    public List<String> getRequiredFields();
    public List getValidOgoneUris();

}
