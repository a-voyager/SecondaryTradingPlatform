package com.swpuiot.stp.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.swpuiot.stp.R;
import com.swpuiot.stp.adapter.ShoppingCarAdapter;

public class ShoppingFragment extends Fragment {

    private ListView listView;
    private ShoppingCarAdapter adapter;
    private Activity activity;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        context =  activity;
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shooping, container, false);
        listView = (ListView) view.findViewById(R.id.lv_shopping);
        adapter = new ShoppingCarAdapter(context);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
