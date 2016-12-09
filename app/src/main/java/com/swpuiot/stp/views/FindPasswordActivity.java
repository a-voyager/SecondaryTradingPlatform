package com.swpuiot.stp.views;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.swpuiot.stp.R;
import com.swpuiot.stp.base.BaseActivity;
import com.swpuiot.stp.base.BaseApplication;
import com.swpuiot.stp.injector.component.ActivityComponent;
import com.swpuiot.stp.injector.component.DaggerActivityComponent;
import com.swpuiot.stp.injector.module.ActivityModule;
import com.swpuiot.stp.interfaces.IFindPasswordView;
import com.swpuiot.stp.presenter.impl.FindPasswordPresenter;
import com.swpuiot.stp.presenter.impl.MainPresenter;
import com.swpuiot.stp.utils.SnackBarUtils;

import org.eclipse.jdt.internal.compiler.ast.FalseLiteral;

import javax.inject.Inject;

import butterknife.BindView;

public class FindPasswordActivity extends BaseActivity implements IFindPasswordView {

    @Inject
    FindPasswordPresenter mFindPasswordPresenter;
    @BindView(R.id.cl_find_password)
    CoordinatorLayout mClMain;
    private Button button;
    private EditText username;
    private EditText email;
    private TextView tv_username_error;
    private TextView tv_email_error;

    @Override
    protected void initializePresenter() {
        mFindPasswordPresenter.attachView(this);
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
        return R.layout.activity_find_password;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        username = (EditText) findViewById(R.id.et_username);
        email = (EditText) findViewById(R.id.et_email);
        tv_email_error = (TextView) findViewById(R.id.tv_showemailerror);
        button = (Button) findViewById(R.id.btn_findpassword);
        tv_username_error = (TextView) findViewById(R.id.tv_shownameerror);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mFindPasswordPresenter.hidetext();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mFindPasswordPresenter.hidenametext();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFindPasswordPresenter.findPasswordOnClick();
            }
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
    public void findPasswordStart() {
        if (email.getText().toString().trim().isEmpty()) {
            tv_email_error.setText("邮箱不能为空");
        }
        if (username.getText().toString().trim().isEmpty()) {
            tv_username_error.setText("用户名不能为空");;
        }
        if (!(email.getText().toString().trim().isEmpty()) && !(username.getText().toString().trim().isEmpty())){
            showSnackBarMsg("success");
        }
    }

    @Override
    public void hidetext() {
        tv_email_error.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideusertext() {
        tv_username_error.setVisibility(View.INVISIBLE);
    }
}
