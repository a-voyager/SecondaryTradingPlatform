package com.swpuiot.stp.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.Button;

import com.swpuiot.stp.R;
import com.swpuiot.stp.base.BaseActivity;
import com.swpuiot.stp.base.BaseApplication;
import com.swpuiot.stp.injector.component.ActivityComponent;
import com.swpuiot.stp.injector.component.DaggerActivityComponent;
import com.swpuiot.stp.injector.module.ActivityModule;
import com.swpuiot.stp.interfaces.IFeedbackView;
import com.swpuiot.stp.presenter.impl.FeedbackPresenter;
import com.swpuiot.stp.utils.SnackBarUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class FeedbackActivity extends BaseActivity implements IFeedbackView {
    @Inject
    FeedbackPresenter mFeedbackPresenter;
    @BindView(R.id.cl_setting_feedback)
    CoordinatorLayout mClFeedback;
    private AlertDialog.Builder dialog;
    private Button feedback;
    @Override
    protected void initializePresenter() {
        mFeedbackPresenter.attachView(this);

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
        return R.layout.activity_feedback;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        feedback= (Button) findViewById(R.id.btn_feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFeedbackPresenter.btnSuccessFeedbackOnClick();
            }
        });

    }

    @Override
    public void showSnackBarMsg(@StringRes int msg) {
        SnackBarUtils.show(mClFeedback, msg);
    }

    @Override
    public void showSnackBarMsg(String msg) {
        SnackBarUtils.show(mClFeedback, msg);
    }


    @Override
    public void startSuccessFeedback() {
        dialog=new AlertDialog.Builder(this);
        dialog.setTitle("反馈");
        dialog.setMessage("感谢你的反馈，我们会尽快解决你的问题。");
        dialog.setPositiveButton("返回", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(FeedbackActivity.this,SettingActivity.class);
                startActivity(intent);
                finish();
            }
        });
        dialog.show();



    }
}
