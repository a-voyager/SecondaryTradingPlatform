package com.swpuiot.stp.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.swpuiot.stp.Fragment.RecommendFragment;
import com.swpuiot.stp.R;
import com.swpuiot.stp.entities.FindGoodsEntity;

import java.util.ArrayList;

/**
 * Created by DELL on 2016/12/26.
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.myViewHolder> {

    private Context mcontext;
    private ArrayList<FindGoodsEntity> mgoodsEntities;

    public RecommendAdapter(Context context, ArrayList<FindGoodsEntity> goodsEntities) {
        mcontext = context;
        mgoodsEntities = goodsEntities;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int positon);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myViewHolder holder = new myViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.recycleview_recommend_item, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder.goodsName.setText(mgoodsEntities.get(position).getName());
        holder.goodsDescription.setText(mgoodsEntities.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(holder.itemView, pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("RecommendFragment", mgoodsEntities.size() + "");
        return mgoodsEntities.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        private TextView goodsName;
        private TextView goodsDescription;

        public myViewHolder(View itemView) {
            super(itemView);
            goodsName = (TextView) itemView.findViewById(R.id.tv_recommend_name);
            goodsDescription = (TextView) itemView.findViewById(R.id.tv__recommend_goodsdescription);

        }
    }

}

