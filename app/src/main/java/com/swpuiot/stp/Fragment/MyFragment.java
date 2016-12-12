package com.swpuiot.stp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.swpuiot.stp.AdapterItem.My_MenuItem;
import com.swpuiot.stp.R;
import com.swpuiot.stp.adapter.My_MenuAdapter;
import com.swpuiot.stp.views.LoginedActivity;
import com.swpuiot.stp.views.SettingActivity;

/**
 * Created by DELL on 2016/12/9.
 */
public class MyFragment extends Fragment {
    private ListView lv_Menu;
    private My_MenuAdapter adapter;
    private TextView btn_showMyself;
    private LoginedActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);
        lv_Menu = (ListView) view.findViewById(R.id.lv_Myitem);
        btn_showMyself = (TextView) view.findViewById(R.id.btn_showmyself);

        adapter = new My_MenuAdapter(getActivity());
        lv_Menu.setAdapter(adapter);
        activity = (LoginedActivity) getActivity();
        super.onCreate(savedInstanceState);
        btn_showMyself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        lv_Menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                activity.showSnackBarMsg(adapter.getItem(i).getTitle());
                switch (i){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        Intent intent = new Intent(getActivity(), SettingActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        return view;
    }



}
