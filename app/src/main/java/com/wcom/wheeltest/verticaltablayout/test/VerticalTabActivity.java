package com.wcom.wheeltest.verticaltablayout.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;

import com.wcom.wheeltest.R;

import java.util.Map;

import q.rorbin.verticaltablayout.TabAdapter;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.QTabView;

/**
 * Created by wiliiamwang on 19/10/2017.
 */

public class VerticalTabActivity extends AppCompatActivity {

    String[] floors = new String[]{"1", "2", "3", "4", "5"};

    VerticalTabLayout vtl;
    ViewPager viewPager;

    NMPArrayMap<String, Map<String, String>> dataMap = new NMPArrayMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (String s : floors) {
            Map<String, String> map = new NMPArrayMap<>();
            for (int i = 0; i < 3; i++) {
                map.put("title " + s + i, "content " + s + i);
            }
            dataMap.put(s, map);
        }

        setContentView(R.layout.activity_vertical_tab);

        viewPager = (ViewPager) findViewById(R.id.vp);
        viewPager.setAdapter(new FloorAdapter(getSupportFragmentManager(), dataMap));

        vtl = (VerticalTabLayout) findViewById(R.id.vertical_tab);

        for (int i = 0; i < floors.length; i++) {

            WTabView wTabView = new WTabView(this);
            wTabView.setTitle(new QTabView.TabTitle.Builder(this)
                    .setContent(floors[i] + "F")
                    .setTextColor(ContextCompat.getColor(this, android.R.color.white),
                            ContextCompat.getColor(this, R.color.floor_blue))
                    .build());
            wTabView.setBackground((i == floors.length - 1) ?
                    R.drawable.floor_number_cornor_white : R.drawable.floor_number_white);
            wTabView.setTag(i);
            wTabView.setOnWTabSelectedListener(new WTabView.OnWTabSelectedListener() {
                @Override
                public void onTabSelected(WTabView tabView) {
                    int position = (Integer) tabView.getTag();

                    if (position == 0) {
                        tabView.setBackground(tabView.isChecked() ?
                                R.drawable.first_floor_number_blue :
                                R.drawable.first_floor_number_white);
                    } else if (position == floors.length - 1) {
                        tabView.setBackground(tabView.isChecked() ?
                                R.drawable.floor_number_cornor_blue :
                                R.drawable.floor_number_cornor_white);
                    } else {
                        tabView.setBackground(tabView.isChecked() ?
                                R.drawable.floor_number_blue :
                                R.drawable.floor_number_white);
                    }
                    Log.e("@W@", "tabView.getTag() : " + tabView.getTag() +
                            "\nposition : " + position);
                    viewPager.setCurrentItem(position);
                }
            });
            vtl.addTab(wTabView);
        }

//        vtl.setupWithViewPager(viewPager);
//        vtl.setOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabView tab, int position) {
//                Log.e("@W@", "onTabSelected position : " + position);
//                Log.e("@W@", "onTabSelected count : " + vtl.getChildCount());
//                viewPager.setCurrentItem(position);
//                if (position == floors.length - 1) {
//                    tab.setBackgroundResource(R.drawable.floor_number_cornor_blue);
//                } else {
//                    tab.setBackgroundResource(R.drawable.floor_number_blue);
//                }
//            }
//
//            @Override
//            public void onTabReselected(TabView tab, int position) {
//                Log.e("@W@", "onTabReSelected position : " + position);
////                if (position == floors.length - 1) {
////                    tab.setBackgroundResource(R.drawable.floor_number_cornor_white);
////                } else {
////                    tab.setBackgroundResource(R.drawable.floor_number_white);
////                }
//            }
//        });
    }

    class FloorAdapter extends FragmentPagerAdapter implements TabAdapter {

        private NMPArrayMap<String, Map<String, String>> mDataMap;

        public FloorAdapter(FragmentManager fm, NMPArrayMap<String, Map<String, String>> map) {
            super(fm);
            mDataMap = map;
        }

        @Override
        public Fragment getItem(int position) {
            return FloorFragment.newInstance((NMPArrayMap<String, String>) mDataMap.get((position + 1) + ""));
        }

        @Override
        public int getCount() {
            return mDataMap.size();
        }

        @Override
        public QTabView.TabTitle getTitle(int position) {
            return new QTabView.TabTitle.Builder(VerticalTabActivity.this)
                    .setContent(mDataMap.keyAt(position) + "F")
                    .setTextColor(ContextCompat.getColor(VerticalTabActivity.this, android.R.color.white),
                            ContextCompat.getColor(VerticalTabActivity.this, R.color.floor_blue))
                    .build();
        }

        @Override
        public int getBackground(int position) {
            return (position == floors.length - 1) ?
                    R.drawable.floor_number_cornor_white :
                    R.drawable.floor_number_white;
        }

        @Override
        public int getBadge(int position) {
            return 0;
        }

        @Override
        public QTabView.TabIcon getIcon(int position) {
            return new QTabView.TabIcon.Builder()
                    .build();
        }
    }

    public int dpToIntPx(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
