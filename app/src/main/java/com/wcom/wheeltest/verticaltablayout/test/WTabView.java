package com.wcom.wheeltest.verticaltablayout.test;

import android.content.Context;

import q.rorbin.verticaltablayout.widget.QTabView;

/**
 * Created by wiliiamwang on 19/10/2017.
 */

public class WTabView extends QTabView {

    public interface OnWTabSelectedListener {
        void onTabSelected(WTabView tabView);
    }

    private OnWTabSelectedListener mListener;

    public WTabView(Context context) {
        super(context);
    }

    public void setOnWTabSelectedListener(OnWTabSelectedListener listener) {
        mListener = listener;
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);

        if (mListener != null) {
            mListener.onTabSelected(this);
        }
    }
}
