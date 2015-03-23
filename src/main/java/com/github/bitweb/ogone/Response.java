package com.github.bitweb.ogone;

public interface Response {

    public static final String HASIGN_FIELD = "SHASIGN";
    public boolean isSuccessful();
    public Object getParameter(String key);

}
