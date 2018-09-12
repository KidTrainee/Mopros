package jp.co.ienter.mopros.utils;

import android.support.annotation.NonNull;

public class PreConditions {
    public static @NonNull <T> T checkNotNull(final T reference) {
        if (reference == null) {
            throw new NullPointerException(reference.getClass().getSimpleName() + " should not be null");
        }
        return reference;
    }
//
}
