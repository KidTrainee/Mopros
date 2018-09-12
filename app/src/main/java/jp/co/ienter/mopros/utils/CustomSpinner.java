package jp.co.ienter.mopros.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * Created by donghv on 8/31/18.
 */

public class CustomSpinner extends android.support.v7.widget.AppCompatSpinner {
    OnItemSelectedListener listener;

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public CustomSpinner(Context context) {
        super(context);
    }

    @Override
    public void setSelection(int position) {
        super.setSelection(position);
        if (listener != null)
            listener.onItemSelected(null, null, position, 0);
    }

    public void setOnItemSelectedEvenIfUnchangedListener(
            OnItemSelectedListener listener) {
        this.listener = listener;
    }
}