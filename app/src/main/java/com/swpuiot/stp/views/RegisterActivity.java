package com.swpuiot.stp.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.swpuiot.stp.R;
import com.swpuiot.stp.base.BaseActivity;
import com.swpuiot.stp.base.BaseApplication;
import com.swpuiot.stp.injector.component.ActivityComponent;
import com.swpuiot.stp.injector.component.DaggerActivityComponent;
import com.swpuiot.stp.injector.module.ActivityModule;
import com.swpuiot.stp.interfaces.IRegisterView;
import com.swpuiot.stp.presenter.impl.RegisterPresenter;
import com.swpuiot.stp.utils.SnackBarUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class RegisterActivity extends BaseActivity implements IRegisterView {

    @Inject
    RegisterPresenter mRegisterPresenter;
    @BindView(R.id.cl_register)
    CoordinatorLayout mClMain;
    private Button btnRegisterToLogin;
    private EditText register_username;
    private EditText register_password;
    private EditText register_sure;
    private EditText register_nickname;
    private EditText register_email;
    private TextView emptySure;
    private TextView emptyUsername;
    private TextView emptyPassword;
    private TextView emptyNickname;
    private TextView emptyEmail;
    @Override
    protected void initializePresenter() {
        mRegisterPresenter.attachView(this);
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
        return R.layout.activity_register;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mRegisterPresenter.onCreate(savedInstanceState);
        btnRegisterToLogin = (Button) findViewById(R.id.btn_registerToLogin);
        register_username = (EditText) findViewById(R.id.et_register_username);
        register_password = (EditText) findViewById(R.id.et_register_password);
        register_sure = (EditText) findViewById(R.id.et_register_sure);
        register_nickname= (EditText) findViewById(R.id.et_register_nickname);
        register_email= (EditText) findViewById(R.id.et_register_password);
        emptySure = (TextView) findViewById(R.id.tv_emptysure);
        emptyUsername= (TextView) findViewById(R.id.tv_emptyusername);
        emptyPassword= (TextView) findViewById(R.id.tv_emptypassword);
        emptyNickname= (TextView) findViewById(R.id.tv_emptynickname);
        emptyEmail= (TextView) findViewById(R.id.tv_emptyeamil);
        btnRegisterToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRegisterPresenter.btnRegisterToLoginOnClick();
            }
        });
        register_sure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mRegisterPresenter.tvSurePassword();
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });


    }

    @Override
    public void showSnackBarMsg(@StringRes int msg) {
        SnackBarUtils.show(mClMain, msg);
    }

    @Override
    public void showSnackBarMsg(String msg) {
        SnackBarUtils.show(mClMain, msg);
    }

    @Override
    public void startRegisterToLoginedActivity() {
        if(register_username.getText().toString().isEmpty()){
            emptyUsername.setText("*用户名不能为空");
        }

         if(register_password.getText().toString().isEmpty()){
            emptyPassword.setText("*密码不能为空");
        }
         if(register_sure.getText().toString().isEmpty()){
            emptySure.setText("*确认密码不能为空");
        }
         else if(!(register_password.getText().toString().equals(register_sure.getText().toString()))){
            emptyPassword.setText("*两次密码不一致");
        }
         if(register_nickname.getText().toString().isEmpty()){
            emptyNickname.setText("*昵称不能为空");
        }
         if(register_email.getText().toString().isEmpty()){
            emptyEmail.setText("*邮箱不能为空");
             return;
        }


        if(!register_username.getText().toString().isEmpty()&&!register_password.getText().toString().isEmpty()&&!register_sure.getText().toString().isEmpty()&&register_password.getText().toString().equals(register_sure.getText().toString()) &&!register_nickname.getText().toString().isEmpty()&&!register_email.getText().toString().isEmpty()) {
            Intent intent = new Intent(this, LoginedActivity.class);
            startActivity(intent);
        }






    }
    @Override
    public void surePassword() {

        if ( register_password.getText().toString().isEmpty()||register_password.getText().toString().equals(register_sure.getText().toString())) {
            emptySure.setText(null);
        } else {
            emptySure.setText("*两次密码不一致");
        }

    }


}