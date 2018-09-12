package jp.ienter.expandableview;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import jp.ienter.expandableview.expandable_layout.AbsExpandableBody;

public interface IExpandableView {
    void setButtonDrawable(Drawable buttonDrawable);
    void setButtonDrawable(@DrawableRes int buttonDrawable);
    void setTitle(String title);
    void setTitle(@StringRes int title);
    void setExpandableBody(FragmentManager fm, Fragment fragment);
}
