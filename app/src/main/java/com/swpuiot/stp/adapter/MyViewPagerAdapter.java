package com.swpuiot.stp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.swpuiot.stp.Fragment.ImageFragment_1;

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
