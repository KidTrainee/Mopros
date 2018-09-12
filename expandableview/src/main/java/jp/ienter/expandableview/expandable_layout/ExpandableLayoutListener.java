package jp.ienter.expandableview.expandable_layout;

import android.view.animation.Animation;

public interface ExpandableLayoutListener {

    void onAnimationStart();

    void onAnimationEnd();

    void onPreOpen();

    void onPreClose();

    void onOpened();

    void onClosed();
}
