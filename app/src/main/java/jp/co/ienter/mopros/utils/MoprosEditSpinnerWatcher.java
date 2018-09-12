package jp.co.ienter.mopros.utils;

import android.text.Editable;
import android.widget.EditText;

import com.example.compoundedittextspinner.EditSpinner;
import com.example.compoundedittextspinner.EditSpinnerWatcher;
import com.example.compoundedittextspinner.TextWatcherCallback;

public class MoprosEditSpinnerWatcher extends EditSpinnerWatcher {

    private EditText mEdt;
    private int MAX_LENGTH;

    public MoprosEditSpinnerWatcher(EditSpinner editText, int max) {
        super(editText);
        mEdt = editText;
        MAX_LENGTH = max;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        super.onTextChanged(s, start, before, count);

        mEdt.removeTextChangedListener(this);
        try {

            String originalString = s.toString();

            int value = 0;

            if (!originalString.isEmpty()) value = Integer.parseInt(originalString);

            if (value > MAX_LENGTH) {
                value = MAX_LENGTH;
            }
            mEdt.setText(String.valueOf(value));
            mEdt.setSelection(mEdt.getText().length());
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        mEdt.addTextChangedListener(this);
    }

}
