package com.github.bitweb.ogone.shaComposer;

import com.github.bitweb.ogone.exceptions.ShaComposerException;

import java.util.Map;

public interface ShaComposer {

    public String compose(Map<String, Object> parameters) throws ShaComposerException;

}
