package com.swpuiot.stp.presenter.impl;

import android.content.Context;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.swpuiot.stp.base.IView;
import com.swpuiot.stp.injector.scrope.ContextLifeCycle;
import com.swpuiot.stp.interfaces.IFeedbackView;
import com.swpuiot.stp.interfaces.IGoodsDescriptionView;
import com.swpuiot.stp.presenter.IPresenter;

import javax.inject.Inject;

/**
 * Created by DELL on 2016/12/22.
 */
public class GoodsDescriptionPresenter implements IPresenter {
    Context mContext;
    IGoodsDescriptionView mIGoodsDescriptionView;

    @Inject
    public GoodsDescriptionPresenter(@ContextLifeCycle("Activity") Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(mContext);
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
        mIGoodsDescriptionView = (IGoodsDescriptionView) v;
    }

    public void addToShoppingCarOnClick() {
        mIGoodsDescriptionView.addtoShoppingCar();
    }
}
