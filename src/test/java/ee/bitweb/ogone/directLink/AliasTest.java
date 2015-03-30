package ee.bitweb.ogone.directLink;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by priit on 27.03.15.
 */
public class AliasTest {

    @Test
    public void testCreateAlias() {
        Alias alias = new Alias();
        Assert.assertTrue(alias != null);
    }

    @Test
    public void testCreateAliasWithAlias() {
        String aliasString = "Some alias";
        Alias alias = new Alias(aliasString);
        Assert.assertTrue(alias.getAlias().equals(aliasString));
    }

    @Test
    public void testToString() {
        String aliasString = "Some alias";
        Alias alias = new Alias(aliasString);
        Assert.assertTrue(alias.toString().equals(aliasString));
    }

    @Test
    public void testCreateAliasWithParams() {
        String alias = "alias";
        String cardName = "name";
        String cardNumber = "number";
        String expiryDate = "date";
        Alias aliasItem = new Alias(alias, cardName, cardNumber, expiryDate);
        Assert.assertTrue(aliasItem.getAlias().equals(alias));
        Assert.assertTrue(aliasItem.getCardName().equals(cardName));
        Assert.assertTrue(aliasItem.getCardNumber().equals(cardNumber));
        Assert.assertTrue(aliasItem.getExpiryDate().equals(expiryDate));
    }

    @Test
    public void testToArrayConvert() {
        String alias = "alias";
        String cardName = "name";
        String cardNumber = "number";
        String expiryDate = "date";
        Alias aliasItem = new Alias(alias, cardName, cardNumber, expiryDate);

        Map<String, String> map = aliasItem.toArray();

        Assert.assertTrue(map.get("ALIAS").equals(alias));
        Assert.assertTrue(map.get("CN").equals(cardName));
        Assert.assertTrue(map.get("CARDNO").equals(cardNumber));
        Assert.assertTrue(map.get("ED").equals(expiryDate));
    }


}
