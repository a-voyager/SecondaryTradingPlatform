package com.swpuiot.stp.presenter.impl;

import android.content.Context;
import android.os.Bundle;

import com.swpuiot.stp.base.IView;
import com.swpuiot.stp.injector.scrope.ContextLifeCycle;
import com.swpuiot.stp.interfaces.IMainView;
import com.swpuiot.stp.presenter.IPresenter;

import javax.inject.Inject;


/**
 * Created by wuhaojie on 2016/7/7 11:28.
 */
public class MainPresenter implements IPresenter {

    public static final String TAG = "MainPresenter";
    private Context mContext;
    private IMainView mIMainView;

    @Inject
    public MainPresenter(@ContextLifeCycle("Activity") Context context) {
        mContext = context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        mIMainView.showSnackBarMsg("Hello MVP!");
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void attachView(IView v) {
        mIMainView = (IMainView) v;
    }


}
