package com.github.bitweb.ogone;

public class Passphrase {

    private String passphrase;

    public Passphrase(String passphrase) {
        if (passphrase == null) {
            throw new IllegalArgumentException("String expected.");
        }
        this.passphrase = passphrase;
    }

    public String toString() {
        return passphrase;
    }
}
