package com.swpuiot.stp.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.swpuiot.stp.R;
import com.swpuiot.stp.adapter.LoginedAdapter;
import com.swpuiot.stp.adapter.MyViewPagerAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private GridView gridView;
    private LoginedAdapter adapter;
    private Context mcontext;
    private ViewPager viewPager;
    private MyViewPagerAdapter mViewPagerAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mcontext = getActivity();
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        gridView = (GridView) view.findViewById(R.id.gv_logined);
        viewPager = (ViewPager) view.findViewById(R.id.image_slide_page);
        mViewPagerAdapter = new MyViewPagerAdapter(this.getChildFragmentManager());
        viewPager.setAdapter(mViewPagerAdapter);
        adapter = new LoginedAdapter(mcontext);
        gridView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
