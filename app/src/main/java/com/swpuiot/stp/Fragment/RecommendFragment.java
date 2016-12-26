package com.swpuiot.stp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.swpuiot.stp.R;
import com.swpuiot.stp.adapter.RecommendAdapter;
import com.swpuiot.stp.entities.FindGoodsEntity;
import com.swpuiot.stp.views.FIndGoodsDescriptionActivity;
import com.swpuiot.stp.views.LoginedActivity;

import java.util.ArrayList;

/**
 * Created by DELL on 2016/12/13.
 */
public class RecommendFragment extends Fragment {
    private static RecommendFragment mrecommendfragment = null;
    private ArrayList<FindGoodsEntity> goodsEntities;
    private RecyclerView mrecyclerView;
    public static final String FINDGOODSINFORMATION = "goodsinformation";
//    public static final String RECOMMENDFRAGMENT = "findGoods";

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        System.out.println(getArguments().get(RECOMMENDFRAGMENT));
//        ArrayList<FindGoodsEntity> findGoodsEntities = (ArrayList<FindGoodsEntity>) getArguments().getSerializable(RECOMMENDFRAGMENT);
//        System.out.println(findGoodsEntities);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        LoginedActivity.toolbar.setTitle("推荐");
        goodsEntities = LoginedActivity.findGoodsEntities;
        mrecyclerView = (RecyclerView) view.findViewById(R.id.recycleview_recommend);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecommendAdapter adapter = new RecommendAdapter(getActivity(), goodsEntities);
        adapter.setmOnItemClickListener(new RecommendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), position + "click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), FIndGoodsDescriptionActivity.class);
                intent.putExtra(FINDGOODSINFORMATION,goodsEntities.get(position));
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int positon) {

            }
        });
        mrecyclerView.setAdapter(adapter);
//        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
//        ArrayList<FindGoodsEntity> goods = (ArrayList<FindGoodsEntity>) getArguments().get(RECOMMENDFRAGMENT);
//        Log.e("RecommendFragment", goods.toString());
        Log.d("RecommendFragment", "new");
        return view;
    }


    public static RecommendFragment newInstance() {

        if (mrecommendfragment == null) {
            Bundle args = new Bundle();
//            args.putByteArray("RECOMMENDFRAGMENT", arrayList);
            mrecommendfragment = new RecommendFragment();
            mrecommendfragment.setArguments(args);
        }
        return mrecommendfragment;
    }

}

