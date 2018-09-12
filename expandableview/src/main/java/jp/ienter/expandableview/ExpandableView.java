package jp.ienter.expandableview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import jp.ienter.expandableview.expandable_layout.AbsExpandableBody;
import jp.ienter.expandableview.expandable_layout.ExpandableLinearLayout;

public class ExpandableView extends RelativeLayout implements IExpandableView {

    private static final String TAG = ExpandableView.class.getSimpleName();
    private Context mContext;

    private ExpandableLinearLayout lnCollapsible;
    private Button btnToggleView;
    private TextView tvTitle;
    private LinearLayout llTitle;
    private ViewGroup mFragmentContainer;

    /**
     * Constructors
     */
    public ExpandableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        inflate(mContext, R.layout.layout_expandable_view, this);
        lnCollapsible = findViewById(R.id.expandable_linear_layout);
        btnToggleView = findViewById(R.id.btn_toggle_view);
        tvTitle = findViewById(R.id.tv_title);
        llTitle = findViewById(R.id.expandable_view_title);
        mFragmentContainer = findViewById(R.id.fl_fragment_container);

        mFragmentContainer.setId(ViewIdGenerator.generateViewId());
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        if (attrs == null) return; // in case mFragmentContainer is init programmatically
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.ExpandableView);

        if (a.hasValue(R.styleable.ExpandableView_title_text)) {
            String titleText = a.getString(R.styleable.ExpandableView_title_text);
            if (!TextUtils.isEmpty(titleText)) tvTitle.setText(titleText);
        }

        if (a.hasValue(R.styleable.ExpandableView_title_text_color)) {
            int titleTextColor = a.getColor(R.styleable.ExpandableView_title_text_color,
                    mContext.getResources().getColor(R.color.colorBlack));
            tvTitle.setTextColor(titleTextColor);
        }
        if (a.hasValue(R.styleable.ExpandableView_title_background_color)) {
            int titleBackgroundColor = a.getColor(R.styleable.ExpandableView_title_background_color,
                    mContext.getResources().getColor(R.color.colorWhite));
            llTitle.setBackgroundColor(titleBackgroundColor);
        }
        if (a.hasValue(R.styleable.ExpandableView_button_drawable)) {
            Drawable drawable = a.getDrawable(R.styleable.ExpandableView_button_drawable);
            btnToggleView.setBackground(drawable);
        }

        btnToggleView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleView(btnToggleView, lnCollapsible);
            }
        });
        a.recycle();
    }

    /*************/


    @Override
    public void setButtonDrawable(Drawable buttonDrawable) {
        try {
            btnToggleView.setBackground(buttonDrawable);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(mContext.getClass().getSimpleName() + "\t" + e.getMessage());
        }
    }

    @Override
    public void setButtonDrawable(@DrawableRes int buttonDrawable) {
        setButtonDrawable(mContext.getResources().getDrawable(buttonDrawable));
    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setTitle(int title) {
        tvTitle.setText(title);
    }

    @Override
    public void setExpandableBody(FragmentManager fm, Fragment fragment) {
        fm.beginTransaction().replace(mFragmentContainer.getId(), fragment).commit();
    }

    private void toggleView(Button btn, ExpandableLinearLayout lnExpand) {
        btn.setSelected(!btn.isSelected());
        lnExpand.setExpanded(!lnExpand.isExpanded());
        if (lnExpand.isExpanded()) lnExpand.expand();
        else lnExpand.collapse();
    }

}
