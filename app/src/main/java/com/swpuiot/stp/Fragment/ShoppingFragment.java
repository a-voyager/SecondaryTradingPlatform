package com.swpuiot.stp.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.swpuiot.stp.R;
import com.swpuiot.stp.adapter.ShoppingCarAdapter;
import com.swpuiot.stp.entities.GoodsEntity;
import com.swpuiot.stp.localbroadcast.ShoppingBroadcast;
import com.swpuiot.stp.views.GoodsDescriptionActivity;
import com.swpuiot.stp.views.LoginedActivity;

import java.util.ArrayList;

public class ShoppingFragment extends Fragment {

    private ListView listView;
    private ShoppingCarAdapter adapter;
    private Activity activity;
    private Context context;
    private Toolbar toolbar;
    private static ShoppingFragment mShoppingFragment = null;
    private ShoppingBroadcast shoppingBroadcast;
    private ArrayList<GoodsEntity> goodsEntitityList;
    private TextView tv_nogoods;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        context = activity;
        goodsEntitityList = LoginedActivity.goodsEntityList;
        View view = inflater.inflate(R.layout.fragment_shooping, container, false);
        tv_nogoods = (TextView) view.findViewById(R.id.tv_shoppingcar_nogoods);
        LoginedActivity.toolbar.setTitle("购物车");
        listView = (ListView) view.findViewById(R.id.lv_shopping);
        if (goodsEntitityList.isEmpty() || goodsEntitityList == null) {
            tv_nogoods.setText("购物车里空空如也，赶快去看看吧");
        } else {
            tv_nogoods.setVisibility(View.GONE);
            adapter = new ShoppingCarAdapter(context, goodsEntitityList);
            listView.setAdapter(adapter);
        }
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static ShoppingFragment newInstance() {

        if (mShoppingFragment == null) {
            Bundle args = new Bundle();
            args.putSerializable("GoodsInformation", "");
            mShoppingFragment = new ShoppingFragment();
            mShoppingFragment.setArguments(args);
        }
        return mShoppingFragment;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        shoppingBroadcast = new ShoppingBroadcast() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                super.onReceive(context, intent);
//                Log.e("ShoppingCarFragmeng","broadCastReceiverSuccess");
//                GoodsEntity goodsEntity = (GoodsEntity) intent.getSerializableExtra(GoodsDescriptionActivity.ADD_TO_SHPOOING_CAR);
//                goodsEntitityList.add(goodsEntity);
//                if (adapter == null) {
//                    adapter = new ShoppingCarAdapter(context, goodsEntitityList);
//                }
//                else {
//                    adapter.notifyDataSetChanged();
//                }
//                listView.setAdapter(adapter);
//            }
//        };
//    }
}
