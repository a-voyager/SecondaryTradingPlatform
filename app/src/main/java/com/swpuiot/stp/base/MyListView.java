package com.swpuiot.stp.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * Created by DELL on 2016/12/18.
 */
public class MyListView extends ListView {
    private GestureDetector mGestureDetector;
    View.OnTouchListener mGestureListener;

    public MyListView(Context context) {
        super(context);
    }


    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(new YScrollDetector());
        setFadingEdgeLength(0);
    }


    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        return mGestureDetector.onTouchEvent(ev);
    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            if (Math.abs(distanceY) >= Math.abs(distanceX)) {
                return true;
            }
            return false;
        }
    }
}


