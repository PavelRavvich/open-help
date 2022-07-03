package com.openhelp.sos.enums;

/**
 * @author Pavel Ravvich.
 */
public enum StatusType {

    NEW("NEW"),
    OPEN("OPEN"),
    PROGRESS("PROGRESS"),
    CLOSE("CLOSE");

    public final String value;

    StatusType(String value) {
        this.value = value;
    }

    public String getType() {
        return value;
    }
}
