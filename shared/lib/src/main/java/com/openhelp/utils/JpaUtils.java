package com.openhelp.utils;

import org.jetbrains.annotations.NotNull;

public class JpaUtils {
    public static String toLike(@NotNull String value) {
        return String.format("%%%s%%", value.trim().toLowerCase());
    }
}
