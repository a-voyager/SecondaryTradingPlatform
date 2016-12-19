package com.swpuiot.stp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.swpuiot.stp.R;
import com.swpuiot.stp.entities.GoodsEntity;
import com.swpuiot.stp.views.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2016/12/19.
 */
public class MainFragmentAdapter extends BaseAdapter {
    private final Context mContext;
    private ViewHolder viewHolder = null;
    private LayoutInflater layoutInflater;
    private ArrayList<GoodsEntity> mgoodsEntities;

    public MainFragmentAdapter(Context context) {
        mContext = context;
        layoutInflater = LayoutInflater.from(mContext);
        mgoodsEntities = MainActivity.getGoodsArray();
        if (mgoodsEntities == null) {
            mgoodsEntities = new ArrayList<>();
            GoodsEntity entity = new GoodsEntity();
            entity.setName("暂时没有商品");
            mgoodsEntities.add(entity);
        }
    }

    @Override
    public int getCount() {
        return mgoodsEntities.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder = new ViewHolder();
        if (view == null) {
            view = layoutInflater.inflate(R.layout.listview_mainfragment_item, null);
            viewHolder.textView = (TextView) view.findViewById(R.id.tv_goodsname);
            viewHolder.simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.sdv_mainfragment_goodsimg);
//            textView.setText(i);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(mgoodsEntities.get(i).getName());
        Uri uri = Uri.parse("http://www.deardull.com/BookStore" + mgoodsEntities.get(i).getImgurl());
        viewHolder.simpleDraweeView.setImageURI(uri);
        return view;
    }

    private class ViewHolder {
        TextView textView;
        SimpleDraweeView simpleDraweeView;
    }
}
