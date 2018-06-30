package net.findeasily.website.util;

import javax.validation.constraints.NotNull;

public final class ToastrUtils {

    public static final String KEY = "toastr";

    public static String success(@NotNull String msg) {
        return build("success", msg);
    }

    public static String info(@NotNull String msg) {
        return build("info", msg);
    }

    public static String warn(@NotNull String msg) {
        return build("warning", msg);
    }

    public static String error(@NotNull String msg) {
        return build("error", msg);
    }

    private static String build(String type, String msg) {
        return String.format("toastr.%s('%s');", type, msg.replace("'", "\'"));
    }
}
