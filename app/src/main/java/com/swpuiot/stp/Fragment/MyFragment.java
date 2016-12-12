package com.swpuiot.stp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.swpuiot.stp.R;
import com.swpuiot.stp.adapter.My_MenuAdapter;

/**
 * Created by DELL on 2016/12/9.
 */
public class MyFragment extends Fragment {
    private ListView lv_Menu;
    private My_MenuAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my,null);
        lv_Menu = (ListView) view.findViewById(R.id.lv_Myitem);
        adapter = new My_MenuAdapter(getActivity());
        lv_Menu.setAdapter(adapter);
        return view;
    }
}
