package com.swpuiot.stp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.swpuiot.stp.AdapterItem.My_MenuItem;
import com.swpuiot.stp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2016/12/5.
 */
public class My_MenuAdapter extends BaseAdapter {

    List<My_MenuItem> mlist=new ArrayList<My_MenuItem>();
    {
        mlist.add(new My_MenuItem("我的收藏",R.drawable.ic_my_likes));
        mlist.add(new My_MenuItem("我的消息",R.drawable.ic_notification_read));
        mlist.add(new My_MenuItem("设置",R.drawable.ic_settings_black_24dp));
    }

    private Context mContext;
    LayoutInflater inflater;
    public My_MenuAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public My_MenuItem getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate;
        My_MenuItem menuItem=mlist.get(i);
        ViewHolder viewHolder;
        if(view==null){
            inflate = inflater.inflate(R.layout.listview_my_menuitem, null);
            viewHolder=new ViewHolder();
            viewHolder.menuImage=(ImageView) inflate.findViewById(R.id.iv_menu);
            viewHolder.title=(TextView) inflate.findViewById(R.id.tv_menu);
            inflate.setTag(viewHolder);
        }
        else{
            inflate=view;
            viewHolder=(ViewHolder) inflate.getTag();
        }
        viewHolder.title.setText(menuItem.getTitle());
        viewHolder.menuImage.setImageResource(menuItem.getImageId());
        return inflate;
    }
    class ViewHolder{
        ImageView menuImage;
        TextView title;
    }
}
