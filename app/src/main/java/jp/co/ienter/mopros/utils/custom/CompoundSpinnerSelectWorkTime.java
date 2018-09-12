package jp.co.ienter.mopros.utils.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;

public class CompoundSpinnerSelectWorkTime extends FrameLayout {

    private Context mContext;
    private ArrayAdapter<String> mSpinnerAdapter;

    @BindView(R.id.layout_compound_spinner_sp)
    Spinner mSpinner;
    @BindView(R.id.layout_compound_spinner_view)
    View mSelectView;

    public CompoundSpinnerSelectWorkTime(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        inflate(mContext, R.layout.layout_compound_spinner_view, this);
        ButterKnife.bind(this, this);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs,  R.styleable.CompoundSpinnerSelectWorkTime);
        if (a.hasValue(R.styleable.CompoundSpinnerSelectWorkTime_entries)) {
            mSpinnerAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1,
                    mContext.getResources().getStringArray(a.getResourceId(R.styleable.CompoundSpinnerSelectWorkTime_entries, 0)));
            setAdapter(mSpinnerAdapter);
        }

        a.recycle();
    }

    public CompoundSpinnerSelectWorkTime setAdapter(@NonNull ArrayAdapter<String> adapter) {
        this.mSpinnerAdapter = adapter;
        mSpinner.setAdapter(adapter);
        return this;
    }

    public CompoundSpinnerSelectWorkTime setWorkTimeListener(@NonNull OnClickListener listener) {
        mSelectView.setOnClickListener(listener);
        return this;
    }

    public CompoundSpinnerSelectWorkTime setOnItemSelectListener(@NonNull final AdapterView.OnItemSelectedListener listener) {
        mSpinner.setOnItemSelectedListener(listener);
        return this;
    }
}
