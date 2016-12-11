package com.swpuiot.stp.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.swpuiot.stp.R;
import com.swpuiot.stp.base.BaseActivity;
import com.swpuiot.stp.base.BaseApplication;
import com.swpuiot.stp.entities.RegisterEntity;
import com.swpuiot.stp.injector.component.ActivityComponent;
import com.swpuiot.stp.injector.component.DaggerActivityComponent;
import com.swpuiot.stp.injector.module.ActivityModule;
import com.swpuiot.stp.interfaces.IRegisterView;
import com.swpuiot.stp.presenter.impl.RegisterPresenter;
import com.swpuiot.stp.utils.GsonUtils;
import com.swpuiot.stp.utils.SnackBarUtils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import butterknife.BindView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends BaseActivity implements IRegisterView {

    @Inject
    RegisterPresenter mRegisterPresenter;
    @BindView(R.id.cl_register)
    CoordinatorLayout mClRegister;
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
    private AsyncHttpClient client;

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
        register_nickname = (EditText) findViewById(R.id.et_register_nickname);
        register_email = (EditText) findViewById(R.id.et_register_password);
        emptySure = (TextView) findViewById(R.id.tv_emptysure);
        emptyUsername = (TextView) findViewById(R.id.tv_emptyusername);
        emptyPassword = (TextView) findViewById(R.id.tv_emptypassword);
        emptyNickname = (TextView) findViewById(R.id.tv_emptynickname);
        emptyEmail = (TextView) findViewById(R.id.tv_emptyeamil);
        client = new AsyncHttpClient();
        btnRegisterToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRegisterPresenter.btnRegisterToLoginOnClick();
            }
        });
        register_sure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mRegisterPresenter.tvSurePassword();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


    }

    @Override
    public void showSnackBarMsg(@StringRes int msg) {
        SnackBarUtils.show(mClRegister, msg);
    }

    @Override
    public void showSnackBarMsg(String msg) {
        SnackBarUtils.show(mClRegister, msg);
    }

    @Override
    public void startRegisterToLoginedActivity() {
        if (register_username.getText().toString().isEmpty()) {
            emptyUsername.setText("*用户名不能为空");
        }

        if (register_password.getText().toString().isEmpty()) {
            emptyPassword.setText("*密码不能为空");
        }
        if (register_sure.getText().toString().isEmpty()) {
            emptySure.setText("*确认密码不能为空");
        } else if (!(register_password.getText().toString().equals(register_sure.getText().toString()))) {
            emptyPassword.setText("*两次密码不一致");
        }
        if (register_nickname.getText().toString().isEmpty()) {
            emptyNickname.setText("*昵称不能为空");
        }
        if (register_email.getText().toString().isEmpty()) {
            emptyEmail.setText("*邮箱不能为空");
            return;
        }


        if (!register_username.getText().toString().isEmpty() && !register_password.getText().toString().isEmpty() && !register_sure.getText().toString().isEmpty() && register_password.getText().toString().equals(register_sure.getText().toString()) && !register_nickname.getText().toString().isEmpty() && !register_email.getText().toString().isEmpty()) {

            RegisterEntity entity = new RegisterEntity("regist", register_username.getText().toString().trim(), register_password.getText().toString(),
                    register_nickname.getText().toString(), register_email.getText().toString());
            String json = GsonUtils.toJson(entity);
            ByteArrayEntity arrayEntity = null;
            try {
                arrayEntity = new ByteArrayEntity(json.getBytes("UTF-8"));
                arrayEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


            client.post(this, "http://www.deardull.com/BookStore/appuser", arrayEntity, "application/json", new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    String src = new String(bytes);
                    Log.d("RegisterActivity", "success");
                    showSnackBarMsg("注册成功，请前往邮箱激活");
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    Log.d("RegisterActivity", "Failed");
                }
            });

        }


    }

    @Override
    public void surePassword() {

        if (register_password.getText().toString().isEmpty() || register_password.getText().toString().equals(register_sure.getText().toString())) {
            emptySure.setText(null);
        } else {
            emptySure.setText("*两次密码不一致");
        }

    }


}