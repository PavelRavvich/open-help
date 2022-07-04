package com.openhelp.group.enums;

/**
 * @author Pavel Ravvich.
 */
public enum RequestStatusType {

    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED"),
    NEW("NEW");

    public final String value;

    RequestStatusType(String value) {
        this.value = value;
    }

    public String getType() {
        return value;
    }
}
