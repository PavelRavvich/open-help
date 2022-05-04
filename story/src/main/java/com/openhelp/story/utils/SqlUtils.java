package com.openhelp.story.utils;


import org.jetbrains.annotations.NotNull;

/**
 * @author Pavel Ravvich.
 */
public class SqlUtils {
    public static String toLikeLower(@NotNull String value) {
        return String.format("%%%s%%", value.trim().toLowerCase());
    }
}
