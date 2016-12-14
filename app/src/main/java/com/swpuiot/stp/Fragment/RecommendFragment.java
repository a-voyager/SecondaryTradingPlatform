package com.swpuiot.stp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swpuiot.stp.R;

/**
 * Created by DELL on 2016/12/13.
 */
public class RecommendFragment extends Fragment {
    private static RecommendFragment mrecommendfragment=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        return view;
    }

    private RecommendFragment() {
    }

    public static RecommendFragment newInstance() {

        if (mrecommendfragment == null) {
            Bundle args = new Bundle();
             mrecommendfragment = new RecommendFragment();
            mrecommendfragment.setArguments(args);
        }
        return mrecommendfragment;
    }
}
