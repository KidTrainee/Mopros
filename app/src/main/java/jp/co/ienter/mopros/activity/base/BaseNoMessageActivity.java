package jp.co.ienter.mopros.activity.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import jp.co.ienter.mopros.utils.PreferencesHelper;

/**
 * Created by donghv on 8/24/18.
 */

public abstract class BaseNoMessageActivity extends LogcatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
