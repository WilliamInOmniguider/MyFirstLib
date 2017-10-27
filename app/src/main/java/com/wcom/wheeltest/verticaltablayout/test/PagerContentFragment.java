package com.wcom.wheeltest.verticaltablayout.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wcom.wheeltest.R;

/**
 * Created by wiliiamwang on 20/10/2017.
 */

public class PagerContentFragment extends Fragment {

    NMPArrayMap<String, String> mFloorDataMap;
    View mView;

    public static PagerContentFragment newInstance(String content) {
        PagerContentFragment fragment = new PagerContentFragment();

        Bundle bundle = new Bundle();
//        bundle.putSerializable("arg_map", map);
        bundle.putString("arg_content", content);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
//            mFloorDataMap = (NMPArrayMap<String, String>) getArguments().getSerializable("arg_map");
            String content = getArguments().getString("arg_content");

            mView = inflater.inflate(R.layout.fragment_pager_content, null, false);

            TextView tv = (TextView) mView.findViewById(R.id.fragment_pager_content_tv);
            tv.setText(content);
        }

        return mView;
    }
}
