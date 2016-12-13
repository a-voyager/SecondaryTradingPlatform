package com.swpuiot.stp.views;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.swpuiot.stp.R;
import com.swpuiot.stp.base.BaseActivity;
import com.swpuiot.stp.base.BaseApplication;
import com.swpuiot.stp.injector.component.ActivityComponent;
import com.swpuiot.stp.injector.component.DaggerActivityComponent;
import com.swpuiot.stp.injector.module.ActivityModule;
import com.swpuiot.stp.interfaces.IUserInformationView;
import com.swpuiot.stp.presenter.impl.UserInformationPresenter;
import com.swpuiot.stp.utils.SnackBarUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class UserInformationActivity extends BaseActivity implements IUserInformationView {
    private LinearLayout ll_change_nickname;
    private TextView tv_nickname;
    private LinearLayout ll_change_sex;
    @Inject
    UserInformationPresenter mUserInformationPresenter;
    @BindView(R.id.cl_user_information)
    CoordinatorLayout mClUserInformation;

    @Override
    protected void initializePresenter() {
        mUserInformationPresenter.attachView(this);

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
        return R.layout.activity_user_information;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        tv_nickname = (TextView) findViewById(R.id.tv_nickname);
        ll_change_nickname = (LinearLayout) findViewById(R.id.ll_change_nickname);
        ll_change_sex = (LinearLayout) findViewById(R.id.ll_change_sex);

        ll_change_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserInformationPresenter.onChangeNickname();
            }
        });
        ll_change_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(UserInformationActivity.this).create();
                View alertView = LayoutInflater.from(UserInformationActivity.this).inflate(R.layout.dialog_change_sex_layout, null);
                alertDialog.setView(alertView);
                alertDialog.show();
                Window window = alertDialog.getWindow();
                RadioGroup radioGroup = (RadioGroup) window.findViewById(R.id.rg_change_sex);
                TextView tv_sex = (TextView) findViewById(R.id.tv_sex);
                assert tv_sex != null;
                if (tv_sex.getText().toString().equals("男"))
                    radioGroup.check(R.id.rb_man);
                if (tv_sex.getText().toString().equals("女"))
                    radioGroup.check(R.id.rb_woman);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        showSnackBarMsg("修改成功");
                        int RadioButtonId = radioGroup.getCheckedRadioButtonId();
                        RadioButton radioButton = (RadioButton) window.findViewById(RadioButtonId);
                        tv_sex.setText(radioButton.getText().toString());
                        alertDialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public void showSnackBarMsg(@StringRes int msg) {
        SnackBarUtils.show(mClUserInformation, msg);
    }

    @Override
    public void showSnackBarMsg(String msg) {
        SnackBarUtils.show(mClUserInformation, msg);
    }

    @Override
    public void startDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setTitle("修改昵称");
        dialog.setContentView(R.layout.dialog_change_nickname_layout);
        dialog.show();
        Window window = dialog.getWindow();
        Button btn_dialog_sure = (Button) window.findViewById(R.id.btn_dialog_sure);
        btn_dialog_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et_changed_nickname = (EditText) window.findViewById(R.id.et_changed_nickname);
                String s = et_changed_nickname.getText().toString().trim();
                showSnackBarMsg("修改成功！");
                tv_nickname.setText(s);
                dialog.dismiss();
            }
        });
        Button btn_dialog_cancled = (Button) window.findViewById(R.id.btn_dialog_cancled);
        btn_dialog_cancled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }
}
