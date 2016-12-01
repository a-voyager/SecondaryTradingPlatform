package com.swpuiot.stp.views;


import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.GridView;
import android.widget.ThemedSpinnerAdapter;

import com.swpuiot.stp.R;
import com.swpuiot.stp.adapter.LoginedAdapter;
import com.swpuiot.stp.base.BaseActivity;
import com.swpuiot.stp.base.BaseApplication;
import com.swpuiot.stp.injector.component.ActivityComponent;
import com.swpuiot.stp.injector.component.DaggerActivityComponent;
import com.swpuiot.stp.injector.module.ActivityModule;
import com.swpuiot.stp.interfaces.ILoginedView;
import com.swpuiot.stp.presenter.impl.LoginedPresenter;
import com.swpuiot.stp.utils.SnackBarUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class LoginedActivity extends BaseActivity implements ILoginedView{

    @Inject
    LoginedPresenter mLoginedPresenter;

    @BindView(R.id.cl_logined)
    CoordinatorLayout mClLogined;
    private GridView gridview;
    private LoginedAdapter adapter;


    @Override
    protected void initializePresenter() {
        mLoginedPresenter.attachView(this);
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
        return R.layout.activity_logined;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mLoginedPresenter.onCreate(savedInstanceState);
        gridview = (GridView) findViewById(R.id.gv_logined);
        adapter = new LoginedAdapter(this);
        gridview.setAdapter(adapter);
    }

    @Override
    public void showSnackBarMsg(@StringRes int msg) {
        SnackBarUtils.show(mClLogined,msg);
    }

    @Override
    public void showSnackBarMsg(String msg) {
        SnackBarUtils.show(mClLogined,msg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_logined,menu);
        return true;

    }
}
