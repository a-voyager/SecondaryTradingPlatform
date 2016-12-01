package com.swpuiot.stp.presenter.impl;

import android.content.Context;
import android.os.Bundle;

import com.swpuiot.stp.base.IView;

import com.swpuiot.stp.injector.scrope.ContextLifeCycle;
import com.swpuiot.stp.interfaces.ILoginedView;
import com.swpuiot.stp.presenter.IPresenter;

import javax.inject.Inject;

/**
 * Created by DELL on 2016/11/29.
 */
public class LoginedPresenter implements IPresenter {
    public static final String TAG = "LoginedActivity";
    private Context mContext;
    private ILoginedView mLoginedView;

    @Inject
    public LoginedPresenter(@ContextLifeCycle("Activity")Context context){
        mContext = context;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        mLoginedView.showSnackBarMsg("登录成功");
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
        mLoginedView = (ILoginedView) v;
    }

}
