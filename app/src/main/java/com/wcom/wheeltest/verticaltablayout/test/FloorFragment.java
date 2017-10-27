package com.wcom.wheeltest.verticaltablayout.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wcom.wheeltest.R;

/**
 * Created by wiliiamwang on 20/10/2017.
 */

public class FloorFragment extends Fragment {

    Context mContext;
    View mView;
    TabLayout mTL;
    ViewPager mVP;

    public static FloorFragment newInstance(NMPArrayMap<String, String> content) {
        FloorFragment fragment = new FloorFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("content", content);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_floor, null, false);

            NMPArrayMap<String, String> content = (NMPArrayMap<String, String>) getArguments().getSerializable("content");

//            TextView tv = (TextView) mView.findViewById(R.id.fragment_floor_tv);
//            tv.setText(content.keyAt(0) + "\n" + content.valueAt(0));
            mVP = (ViewPager) mView.findViewById(R.id.fragment_floor_vp);
            mVP.setAdapter(new FloorExhibitAdapter(getChildFragmentManager(), content));

            mTL = (TabLayout) mView.findViewById(R.id.fragment_floor_tl);
            mTL.setupWithViewPager(mVP);
        }

        return mView;
    }

    class FloorExhibitAdapter extends FragmentPagerAdapter {

        private NMPArrayMap<String, String> mFloorDataMap;

        public FloorExhibitAdapter(FragmentManager fm, NMPArrayMap<String, String> floorDataMap) {
            super(fm);
            mFloorDataMap = floorDataMap;
        }

        @Override
        public int getCount() {
            return mFloorDataMap.size();
        }

        @Override
        public Fragment getItem(int position) {
            Log.e("@W@", "floor fragment getItem position : " + position);
            return PagerContentFragment.newInstance(mFloorDataMap.valueAt(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFloorDataMap.keyAt(position);
        }
    }
}
