package jp.co.ienter.mopros.utils.custom;

import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;
import android.widget.RemoteViews;

/**
 * Format the text view to 00:00 for every device.
 */
@RemoteViews.RemoteView
public class CustomChronometer extends Chronometer {
    private boolean mIsFormatChanged = false;

    public CustomChronometer(Context context) {
        super(context);
        init();
    }

    public CustomChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomChronometer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setFormat("0%s");
            setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    if ((SystemClock.elapsedRealtime() - chronometer.getBase() > 599000) && !mIsFormatChanged) {
                        setFormat("%s");
                        mIsFormatChanged = true;
                        setOnChronometerTickListener(null);
                    }
                }
            });
        }
    }
}