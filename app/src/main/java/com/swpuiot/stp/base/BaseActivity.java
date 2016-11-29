package com.swpuiot.stp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.swpuiot.stp.R;
import com.swpuiot.stp.injector.interfaces.IConfigInjector;

import butterknife.ButterKnife;


/**
 * Created by wuhaojie on 2016/7/7 10:42.
 */
public abstract class BaseActivity extends AppCompatActivity implements IConfigInjector, IView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        ButterKnife.bind(this);
        initializeInjector();
        initializePresenter();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews(savedInstanceState);
    }

    protected abstract void initializePresenter();


}
