package com.swpuiot.stp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.swpuiot.stp.AdapterItem.ShoppingItem;
import com.swpuiot.stp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2016/12/6.
 */
public class ShoppingCarAdapter extends BaseAdapter {

    private Context mcontext;
    private ImageView imageView;
    private TextView textView;
    LayoutInflater layoutInflater;
    List<ShoppingItem> list = new ArrayList<>();

    {
        list.add(new ShoppingItem(R.drawable.huangyueying, "Android第一行代码"));
        list.add(new ShoppingItem(R.drawable.zhugeliang,"Android权威编程指南"));
        list.add(new ShoppingItem(R.drawable.ic_jike_login,"JAVA编程思想"));
    }

    public ShoppingCarAdapter(Context context) {
        mcontext = context;
        layoutInflater = LayoutInflater.from(mcontext);
    }

    @Override
    public int getCount() {
        return list.size();
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
        view = layoutInflater.inflate(R.layout.listview_shoppingcaritem,viewGroup,false);
        imageView = (ImageView) view.findViewById(R.id.goods_img);
        textView = (TextView) view.findViewById(R.id.goods_name);
        imageView.setImageResource(list.get(i).getImgId());
        textView.setText(list.get(i).getShopname());
        return view;
    }
}
