package org.iflab.wecentermobileandroidrestructure.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by hcjcch on 15/6/14.
 */
public class InterceptRelativeLayout extends RelativeLayout {
    public InterceptRelativeLayout(Context context) {
        super(context);
    }

    public InterceptRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}