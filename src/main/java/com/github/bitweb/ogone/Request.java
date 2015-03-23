package com.github.bitweb.ogone;

import java.util.List;
import java.util.Map;

public interface Request {

    public Map toArray();
    public String getOgoneUri();
    public String getShaSign();
    public List<String> getRequiredFields();
    public List getValidOgoneUris();

}
