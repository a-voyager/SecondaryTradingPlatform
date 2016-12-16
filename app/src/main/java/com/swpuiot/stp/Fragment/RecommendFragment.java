package com.swpuiot.stp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swpuiot.stp.R;
import com.swpuiot.stp.views.LoginedActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2016/12/13.
 */
public class RecommendFragment extends Fragment {
    private static RecommendFragment mrecommendfragment = null;
    private List<String> mDatas;
    private RecyclerView mrecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        LoginedActivity.toolbar.setTitle("推荐");
        initData();
        mrecyclerView = (RecyclerView) view.findViewById(R.id.recycleview_recommend);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mrecyclerView.setAdapter(new myAdapter());
        Log.d("RecommendFragment","new");
        return view;
    }

    private RecommendFragment() {
    }

    protected void initData(){
        mDatas = new ArrayList<String>();
        for (int i = 'A';i<'z';i++){
            mDatas.add(""+(char)i);
        }
    }

    public static RecommendFragment newInstance() {

        if (mrecommendfragment == null) {
            Bundle args = new Bundle();
            mrecommendfragment = new RecommendFragment();
            mrecommendfragment.setArguments(args);
        }
        return mrecommendfragment;
    }
    class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder>{


        @Override
        public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            myViewHolder holder = new myViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.recycleview_recommend_item,parent,false));

            return holder;
        }

        @Override
        public void onBindViewHolder(myViewHolder holder, int position) {
            holder.textView.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            Log.d("RecommendFragment",mDatas.size()+"");
            return mDatas.size();
        }

        class myViewHolder extends RecyclerView.ViewHolder{
            private TextView textView;
            public myViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.tv_recommend_name);
            }
        }
    }


}
