package com.swpuiot.stp.views;


import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.swpuiot.stp.R;
import com.swpuiot.stp.adapter.MyAdapter;
import com.swpuiot.stp.adapter.My_MenuAdapter;
import com.swpuiot.stp.base.BaseActivity;
import com.swpuiot.stp.base.BaseApplication;
import com.swpuiot.stp.injector.component.ActivityComponent;
import com.swpuiot.stp.injector.component.DaggerActivityComponent;
import com.swpuiot.stp.injector.module.ActivityModule;
import com.swpuiot.stp.interfaces.IMyView;
import com.swpuiot.stp.presenter.impl.MyPresenter;
import com.swpuiot.stp.presenter.impl.RegisterPresenter;
import com.swpuiot.stp.utils.SnackBarUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class MyActivity extends BaseActivity implements IMyView {
    @Inject
    MyPresenter mMyPresenter;
    @BindView(R.id.cl_my)
    CoordinatorLayout mClMy;
    private ListView my;
    private ListView menu;
    private MyAdapter myAdapter;
    private My_MenuAdapter my_menuAdapter;
    @Override
    protected void initializePresenter() {
        mMyPresenter.attachView(this);
    }

    @Override
    public void initializeInjector() {
        ActivityComponent component = DaggerActivityComponent
                .builder()
                .appComponent(((BaseApplication) getApplication())
                        .getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
        component.inject(this);

    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_my;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mMyPresenter.onCreate(savedInstanceState);
        my= (ListView) findViewById(R.id.lv_my);
        menu= (ListView) findViewById(R.id.lv_mymenu);

        myAdapter=new MyAdapter(this);
        my_menuAdapter=new My_MenuAdapter(this);
        my.setAdapter(myAdapter);
        menu.setAdapter(my_menuAdapter);
        my.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    @Override
    public void showSnackBarMsg(@StringRes int msg) {
        SnackBarUtils.show(mClMy, msg);
    }

    @Override
    public void showSnackBarMsg(String msg) {
        SnackBarUtils.show(mClMy, msg);
    }
}
