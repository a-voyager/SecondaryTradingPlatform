package com.swpuiot.stp.presenter.impl;

import android.content.Context;
import android.os.Bundle;

import com.swpuiot.stp.base.IView;
import com.swpuiot.stp.injector.scrope.ContextLifeCycle;
import com.swpuiot.stp.interfaces.IFeedbackView;
import com.swpuiot.stp.interfaces.IUserInformationView;
import com.swpuiot.stp.presenter.IPresenter;

import javax.inject.Inject;

/**
 * Created by DuZeming on 2016/12/11.
 */
public class FeedbackPresenter implements IPresenter {
    Context mContext;
    IFeedbackView mIFeedbackView;
    @Inject
    public FeedbackPresenter(@ContextLifeCycle("Activity") Context context) {
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
        mIFeedbackView = (IFeedbackView) v;
    }
}
