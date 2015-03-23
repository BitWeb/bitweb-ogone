package com.github.bitweb.ogone.directLink;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Alias {

    private String alias;
    private String cardName;
    private String cardNumber;
    private String expiryDate;

    public Alias() {
    }

    public Alias(String alias) {
//        if (alias == null) {
//            throw new IllegalArgumentException("Alias cannot be empty.")
//        }

//        if (alias.length() > 50) {
//            throw new IllegalArgumentException("Alias is too long.")
//        }
//
//        if (!alias.matches("^[a-zA-Z0-9_-]+")) {
//            throw new IllegalArgumentException("Alias cannot contain special characters.")
//        }

        this.alias = alias;
    }

    public Alias(String alias, String cardName, String cardNumber, String expiryDate) {
        this(alias);

        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    public String getAlias() {
        return alias;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String toString() {
        return alias;
    }

    public Map<String, String> toArray() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("ALIAS", alias);
        map.put("CN", cardName);
        map.put("CARDNO", cardNumber);
        map.put("ED", expiryDate);
        return map;
    }

}
