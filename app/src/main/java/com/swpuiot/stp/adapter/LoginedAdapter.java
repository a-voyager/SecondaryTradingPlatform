package com.swpuiot.stp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.swpuiot.stp.AdapterItem.LoginedItem;
import com.swpuiot.stp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2016/11/30.
 */
public class LoginedAdapter extends BaseAdapter {

    private Context mcontext;
    private LayoutInflater layoutInflater;
    private ImageView imageView;
    private TextView textView;

    public LoginedAdapter(Context context) {
        mcontext = context;
        layoutInflater = LayoutInflater.from(mcontext);
    }

    public List<LoginedItem> list = new ArrayList<>();
    {
        list.add(new LoginedItem("天猫", R.drawable.zhugeliang));
        list.add(new LoginedItem("聚划算",R.drawable.huangyueying));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.gridview_item,null);
        imageView = (ImageView) view.findViewById(R.id.item_imageView);
        imageView.setImageResource(list.get(i).getImgId());
        textView = (TextView) view.findViewById(R.id.item_textView);
        textView.setText(list.get(i).getText());
        return view;
    }
}
