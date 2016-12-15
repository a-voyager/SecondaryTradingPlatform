package com.swpuiot.stp.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
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
    private Toolbar toolbar;
    private static ShoppingFragment mShoppingFragment=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        context = activity;
        View view = inflater.inflate(R.layout.fragment_shooping, container, false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar_loginedin);
        toolbar.setTitle("购物车");
        listView = (ListView) view.findViewById(R.id.lv_shopping);
        adapter = new ShoppingCarAdapter(context);
        listView.setAdapter(adapter);
        return view;
    }

    private ShoppingFragment() {
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static ShoppingFragment newInstance() {

        if (mShoppingFragment == null) {
            Bundle args = new Bundle();
             mShoppingFragment = new ShoppingFragment();
            mShoppingFragment.setArguments(args);
        }
        return mShoppingFragment;
    }
}
