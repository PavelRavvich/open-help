package com.openhelp.profile.enums;

/**
 * @author Pavel Ravvich.
 */
public enum OperationType {
    CREATE("CREATE"),
    READ_OWN("READ_OWN"),
    READ_ANY("READ_ANY"),
    UPDATE_OWN("UPDATE_OWN"),
    UPDATE_ANY("UPDATE_ANY"),
    DELETE_OWN("DELETE_OWN"),
    DELETE_ANY("DELETE_ANY");

    public final String value;

    OperationType(String value) {
        this.value = value;
    }

    public String getType() {
        return value;
    }
}
