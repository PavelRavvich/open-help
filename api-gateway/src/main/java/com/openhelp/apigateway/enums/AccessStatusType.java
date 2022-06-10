package com.openhelp.apigateway.enums;

/**
 * @author Pavel Ravvich.
 */
public enum AccessStatusType {
    OPEN("OPEN"),
    CLOSE("CLOSE");

    public final String value;

    AccessStatusType(String value) {
        this.value = value;
    }

    public String getType() {
        return value;
    }
}
