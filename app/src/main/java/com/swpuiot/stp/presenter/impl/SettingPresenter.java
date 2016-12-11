package com.swpuiot.stp.presenter.impl;

import android.content.Context;
import android.os.Bundle;

import com.swpuiot.stp.base.IView;
import com.swpuiot.stp.injector.scrope.ContextLifeCycle;
import com.swpuiot.stp.interfaces.IRegisterView;
import com.swpuiot.stp.interfaces.ISettingView;
import com.swpuiot.stp.presenter.IPresenter;

import javax.inject.Inject;

/**
 * Created by DuZeming on 2016/12/9.
 */
public class SettingPresenter implements IPresenter {
    Context mContext;
    ISettingView mISettingView;
    @Inject
    public SettingPresenter(@ContextLifeCycle("Activity") Context context) {
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
        mISettingView = (ISettingView) v;
    }
    public void llSettingToUserInformationOnClick(){
        mISettingView.startSettingToUserInformation();
    }
    public void llTakePhoto(){
        mISettingView.startTakePhoto();
    }
    public void llSettingToNormalSetting(){
        mISettingView.startSettingToNormalSetting();
    }
    public void llSetingToFeedback(){
        mISettingView.startSettingToFeedback();
    }
}
