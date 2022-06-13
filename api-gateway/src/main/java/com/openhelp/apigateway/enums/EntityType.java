package com.openhelp.apigateway.enums;

/**
 * @author Pavel Ravvich.
 */
public enum EntityType {
    USER("USER"),
    ROLE("ROLE"),
    SOS("SOS"),
    STORY("STORY"),
    GROUP("GROUP"),
    MESSAGE("MESSAGE"),
    NOTIFICATION("NOTIFICATION");

    public final String value;

    EntityType(String value) {
        this.value = value;
    }

    public String getType() {
        return value;
    }
}
