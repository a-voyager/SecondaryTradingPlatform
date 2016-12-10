package com.swpuiot.stp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swpuiot.stp.Fragment.ImageFragment_1;
import com.swpuiot.stp.Fragment.ImageFragment_2;
import com.swpuiot.stp.Fragment.MainFragment;
import com.swpuiot.stp.Fragment.MyFragment;
import com.swpuiot.stp.Fragment.ShoppingFragment;
import com.swpuiot.stp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2016/12/9.
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.i("Adapter", "pos: "+position);
//        switch (position){
//            case 0:
//                return new ImageFragment_1();
//            case 1:
//                return new ImageFragment_1();
//            case 2:
//                return new ImageFragment_2();
//            case 3:
//                return new ImageFragment_2();
//        }
        return new ImageFragment_1();
    }

    @Override
    public int getCount() {
        return 4;
    }
}
