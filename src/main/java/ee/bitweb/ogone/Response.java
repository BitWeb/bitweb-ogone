package ee.bitweb.ogone;

public interface Response {

    public boolean isSuccessful();
    public Object getParameter(String key);

}
