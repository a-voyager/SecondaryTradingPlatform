package com.swpuiot.stp.presenter.impl;

import android.content.Context;
import android.os.Bundle;

import com.swpuiot.stp.base.IView;
import com.swpuiot.stp.injector.scrope.ContextLifeCycle;
import com.swpuiot.stp.interfaces.IRegisterView;
import com.swpuiot.stp.presenter.IPresenter;

import javax.inject.Inject;

/**
 * Created by DELL on 2016/12/1.
 */
public class RegisterPresenter implements IPresenter {

    Context mContext;
    IRegisterView mIRegisterView;

    @Inject
    public RegisterPresenter(@ContextLifeCycle("Activity") Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        mIRegisterView = (IRegisterView) v;
    }

    public void btnRegisterToLoginOnClick() {
        mIRegisterView.startRegisterToLoginedActivity();
    }

    public void tvSurePassword() {
        mIRegisterView.surePassword();
    }
}
