package com.swpuiot.stp.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.swpuiot.stp.R;
import com.swpuiot.stp.adapter.LoginedAdapter;
import com.swpuiot.stp.adapter.MyViewPagerAdapter;
import com.swpuiot.stp.views.LoginedActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    public static String TAG = "Main_Fragment";
    private GridView gridView;
    private LoginedAdapter adapter;
    //    private Context mcontext;
    private ViewPager viewPager;
    private MyViewPagerAdapter mViewPagerAdapter;
    private Toolbar toolbar;
    private static MainFragment mMainFragment = null;

    private MainFragment() {
    }

    public static MainFragment newInstance() {
        if (mMainFragment == null) {
            Bundle args = new Bundle();

            mMainFragment = new MainFragment();
            mMainFragment.setArguments(args);
            Log.d("MainFragment", "new");
        }
        return mMainFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        mcontext = getActivity();
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        gridView = (GridView) view.findViewById(R.id.gv_logined);
        viewPager = (ViewPager) view.findViewById(R.id.image_slide_page);
//        toolbar = (LoginedActivity)getActivity()
        toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar_loginedin);
        toolbar.setTitle("首页");
        mViewPagerAdapter = new MyViewPagerAdapter(this.getChildFragmentManager());
        viewPager.setAdapter(mViewPagerAdapter);
        adapter = new LoginedAdapter(getActivity());
        gridView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
