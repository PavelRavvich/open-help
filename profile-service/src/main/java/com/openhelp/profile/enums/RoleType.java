package com.openhelp.profile.enums;

/**
 * @author Pavel Ravvich.
 */
public enum RoleType {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    public final String value;

    RoleType(String value) {
        this.value = value;
    }

    public String getType() {
        return value;
    }
}
