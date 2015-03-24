package ee.bitweb.ogone.shaComposer;

import java.util.Map;

public class MockShaComposer implements ShaComposer {

    private String hashString;

    public MockShaComposer(String hashString) {
        this.hashString = hashString;
    }

    public String compose(Map<String, Object> parameters) {
        return hashString;
    }

}
