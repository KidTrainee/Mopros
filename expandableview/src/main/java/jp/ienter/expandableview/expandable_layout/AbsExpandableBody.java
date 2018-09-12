package jp.ienter.expandableview.expandable_layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class AbsExpandableBody extends ViewGroup {

    private final Context mContext;
    private LayoutInflater mLayoutInflater;

    public AbsExpandableBody(Context context) {
        super(context);
        mContext = context;
        init();
    }

    private void init() {
        mLayoutInflater = LayoutInflater.from(mContext);
        onCreateView(mLayoutInflater, this);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container);
}
