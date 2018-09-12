package jp.ienter.expandableview.expandable_layout;


import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({IExpandableLayout.HORIZONTAL, IExpandableLayout.VERTICAL})
public @interface Orientation {
}
