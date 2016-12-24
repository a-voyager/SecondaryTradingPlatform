package com.swpuiot.stp.Fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.swpuiot.stp.R;
import com.swpuiot.stp.adapter.MainFragmentAdapter;
import com.swpuiot.stp.adapter.MyViewPagerAdapter;
import com.swpuiot.stp.base.MyListView;
import com.swpuiot.stp.entities.GoodsEntity;
//import com.swpuiot.stp.entities.GoodsListEntiey;
import com.swpuiot.stp.utils.GsonUtils;
import com.swpuiot.stp.views.GoodsDescriptionActivity;
import com.swpuiot.stp.views.LoginedActivity;
import com.swpuiot.stp.views.MainActivity;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    public static String GoodsInfo = "Main_Fragment";
    private GridView gridView;
    private MainFragmentAdapter adapter;
    //    private LoginedAdapter adapter;
    //    private Context mcontext;
    private ViewPager viewPager;
    private MyViewPagerAdapter mViewPagerAdapter;
    //    private Toolbar toolbar;
    private MyListView myListView;
    private static MainFragment mMainFragment = null;
    private ArrayList<GoodsEntity> goodsArray;

//    private MainFragment() {
//    }

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
//        getGoods();
        adapter = new MainFragmentAdapter(getActivity());
        myListView = (MyListView) view.findViewById(R.id.lv_showgoods);
        myListView.addHeaderView(initheadView());
        myListView.setAdapter(adapter);
//        gridView = (GridView) view.findViewById(R.id.gv_logined);
//        viewPager = (ViewPager) view.findViewById(R.id.image_slide_page);
//        toolbar = (LoginedActivity)getActivity()
        LoginedActivity.toolbar.setTitle("主页");
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public  void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), i+"", Toast.LENGTH_SHORT).show();
                Log.e("MainFragment","OnitemClick");
                Intent intent = new Intent(getActivity(), GoodsDescriptionActivity.class);
                intent.putExtra(GoodsInfo, MainActivity.getGoodsArray().get(i-1));
                startActivity(intent);
//                startActivityForResult(intent,1);
            }
        });
//        mViewPagerAdapter = new MyViewPagerAdapter(this.getChildFragmentManager());
//        viewPager.setAdapter(mViewPagerAdapter);
//        adapter = new LoginedAdapter(getActivity());
//        gridView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public View initheadView() {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.listview_mainfragment_head, null);
        viewPager = (ViewPager) view.findViewById(R.id.vp_fragmentmain_goods);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new ImageFragment_1();
                    case 1:
                        return new ImageFragment_2();
                    case 2:
                        return new ImageFragment_1();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.e("MainFragment", "attach");
        Log.e("MainFragment", mMainFragment + "");
    }
}
