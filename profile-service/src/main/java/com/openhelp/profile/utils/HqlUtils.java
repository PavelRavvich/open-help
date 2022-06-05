package com.openhelp.profile.utils;

import org.jetbrains.annotations.NotNull;

/**
 * @author Pavel Ravvich.
 */
public class HqlUtils {
    @NotNull
    public static String toLikeLower(@NotNull String value) {
        return String.format("%%%s%%", value.trim().toLowerCase());
    }
}
