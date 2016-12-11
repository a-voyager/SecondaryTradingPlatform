package com.swpuiot.stp.views;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.swpuiot.stp.R;
import com.swpuiot.stp.base.BaseActivity;
import com.swpuiot.stp.base.BaseApplication;
import com.swpuiot.stp.injector.component.ActivityComponent;
import com.swpuiot.stp.injector.component.DaggerActivityComponent;
import com.swpuiot.stp.injector.module.ActivityModule;
import com.swpuiot.stp.interfaces.ISettingView;
import com.swpuiot.stp.presenter.impl.SettingPresenter;
import com.swpuiot.stp.utils.SnackBarUtils;

import javax.inject.Inject;

import butterknife.BindView;


public class SettingActivity extends BaseActivity implements ISettingView {
    @Inject
    SettingPresenter mSettingPresenter;
    @BindView(R.id.ll_setting)
    LinearLayout mClSetting;
    private LinearLayout settingToUserInformation;
    private LinearLayout settingToNormalSetting;
    private LinearLayout settingToFeedback;
    @Override
    protected void initializePresenter() {
        mSettingPresenter.attachView(this);
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
        return R.layout.activity_setting;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        settingToUserInformation= (LinearLayout) findViewById(R.id.ll_setting_userinformation);
        settingToUserInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSettingPresenter.llSettingToUserInformationOnClick();
            }
        });
        settingToNormalSetting= (LinearLayout) findViewById(R.id.ll_setting_normalsetting);
        settingToNormalSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSettingPresenter.llSettingToNormalSetting();
            }
        });
        settingToFeedback= (LinearLayout) findViewById(R.id.ll_setting_feedback);
        settingToFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSettingPresenter.llSetingToFeedback();
            }
        });

    }

    @Override
    public void showSnackBarMsg(@StringRes int msg) {
        SnackBarUtils.show(mClSetting, msg);
    }

    @Override
    public void showSnackBarMsg(String msg) {
        SnackBarUtils.show(mClSetting, msg);
    }


    @Override
    public void startSettingToUserInformation() {
        Intent intent=new Intent(this,UserInformationActivity.class);
        startActivity(intent);
    }

    @Override
    public void startTakePhoto() {

    }

    @Override
    public void startSettingToNormalSetting() {
        Intent intent=new Intent(this,NormalSettingActivity.class);
        startActivity(intent);
    }

    @Override
    public void startSettingToFeedback() {
        Intent intent=new Intent(this,FeedbackActivity.class);
        startActivity(intent);
    }
}
