package com.swpuiot.stp.presenter.impl;

import android.content.Context;
import android.os.Bundle;

import com.swpuiot.stp.base.IView;
import com.swpuiot.stp.injector.scrope.ContextLifeCycle;
import com.swpuiot.stp.interfaces.IAddGoodsView;
import com.swpuiot.stp.interfaces.IFeedbackView;
import com.swpuiot.stp.presenter.IPresenter;

import javax.inject.Inject;

/**
 * Created by DELL on 2016/12/16.
 */
public class AddGoodsPresenter implements IPresenter {
    Context mContext;
    IAddGoodsView mAddGoodsView;

    @Inject
    public AddGoodsPresenter(@ContextLifeCycle("Activity") Context context) {
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
        mAddGoodsView = (IAddGoodsView) v;
    }

    public void AddImageOnclick() {
        mAddGoodsView.addImage();
    }
}
