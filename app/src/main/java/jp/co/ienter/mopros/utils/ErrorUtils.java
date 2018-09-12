package jp.co.ienter.mopros.utils;

public class ErrorUtils {
    public static String getError(Throwable error) {
        return error.getMessage() != null ? error.getMessage() : "System Error";
    }
}
