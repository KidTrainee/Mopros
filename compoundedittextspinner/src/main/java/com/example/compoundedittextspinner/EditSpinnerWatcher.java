package com.example.compoundedittextspinner;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * This is used to watch for edits to the text view.
 */
public class EditSpinnerWatcher implements TextWatcher {

    private TextWatcherCallback mCallback;

    public EditSpinnerWatcher(TextWatcherCallback callback) {
        this.mCallback = callback;
    }

    public void afterTextChanged(Editable s) {
        mCallback.doAfterTextChanged();
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mCallback.doBeforeTextChanged();
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

}
