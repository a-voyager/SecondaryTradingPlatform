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
public class ShareFragment extends Fragment {
    private static ShareFragment mShareFragment = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        return view;
    }

    private ShareFragment() {
    }

    public static ShareFragment newInstance() {
        if (mShareFragment == null) {
            Bundle args = new Bundle();

            mShareFragment = new ShareFragment();
            mShareFragment.setArguments(args);
        }
        return mShareFragment;
    }
}
