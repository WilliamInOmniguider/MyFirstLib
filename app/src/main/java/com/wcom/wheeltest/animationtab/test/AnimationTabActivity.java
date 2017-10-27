package com.wcom.wheeltest.animationtab.test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.wcom.wheeltest.R;

/**
 * Created by wiliiamwang on 25/10/2017.
 */

public class AnimationTabActivity extends AppCompatActivity {

    private final String TAB_TAG_1 = "tab_tag_1";
    private final String TAB_TAG_2 = "tab_tag_2";
    private final String TAB_TAG_3 = "tab_tag_3";

    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_tab);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_1).setIndicator(
                generateTabView("Tab 1", R.mipmap.ic_launcher)),
                FragmentOne.class,
                null);

        mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_2).setIndicator(
                generateTabView("Tab 2", R.mipmap.ic_launcher)),
                FragmentTwo.class,
                null);

        mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_3).setIndicator(
                generateTabView("Tab 3", R.mipmap.ic_launcher)),
                FragmentThree.class,
                null);

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
//                updateTabView(mTabHost);
            }
        });

        mTabHost.onTabChanged(TAB_TAG_1);
    }

    private View generateTabView(String str, @DrawableRes int iconResId) {
        TextView tv = new TextView(this);
        tv.setHeight(getTabBarHeight());
        tv.setPadding(0, dpToIntPx(5), 0, dpToIntPx(5));
        tv.setText(str);
        tv.setGravity(Gravity.CENTER);
        tv.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(this, iconResId), null, null);
        tv.setBackgroundColor(Color.parseColor("#eeeeee"));

        return tv;
    }

    public int getTabBarHeight() {
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        } else {
            return dpToIntPx(55);
        }
    }

    public int dpToIntPx(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    public Drawable getDrawable(Context context, int drawableId) {
        return ContextCompat.getDrawable(context, drawableId);
    }
}
