package com.swpuiot.stp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.swpuiot.stp.AdapterItem.MyItem;
import com.swpuiot.stp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2016/12/5.
 */

public class MyAdapter extends BaseAdapter {
    List<MyItem> mList = new ArrayList<MyItem>();
    {
        mList.add(new MyItem("点击登录账号", R.drawable.ic_jike_login));
    }
    private Context mContext;
    LayoutInflater inflater;
    public MyAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate;
        MyItem myItem=mList.get(i);
        ViewHolder viewHolder;
        if(view==null){
            inflate = inflater.inflate(R.layout.listview_myitem, null);
            viewHolder=new ViewHolder();
            viewHolder.myImage=(ImageView) inflate.findViewById(R.id.iv_my);
            viewHolder.title=(TextView) inflate.findViewById(R.id.tv_my);
            inflate.setTag(viewHolder);
        }
        else{
            inflate=view;
            viewHolder=(ViewHolder) inflate.getTag();
        }
        viewHolder.title.setText(myItem.getTitle());
        viewHolder.myImage.setImageResource(myItem.getImageId());
        return inflate;
    }
    class ViewHolder{
        ImageView myImage;
        TextView title;
    }
}
