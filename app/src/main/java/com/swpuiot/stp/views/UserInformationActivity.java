package com.swpuiot.stp.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.swpuiot.stp.R;
import com.swpuiot.stp.base.BaseActivity;
import com.swpuiot.stp.base.BaseApplication;
import com.swpuiot.stp.injector.component.ActivityComponent;
import com.swpuiot.stp.injector.component.DaggerActivityComponent;
import com.swpuiot.stp.injector.module.ActivityModule;
import com.swpuiot.stp.interfaces.ISettingView;
import com.swpuiot.stp.interfaces.IUserInformationView;
import com.swpuiot.stp.presenter.impl.SettingPresenter;
import com.swpuiot.stp.presenter.impl.UserInformationPresenter;
import com.swpuiot.stp.utils.SnackBarUtils;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;

public class UserInformationActivity extends BaseActivity implements IUserInformationView {
    @Inject
    UserInformationPresenter mUserInformationPresenter;
    @BindView(R.id.cl_user_information)
    CoordinatorLayout mClUserInformation;
    private LinearLayout llAge;
    private TextView tvAge;
    private LinearLayout llchangesex;
    private LinearLayout llchangenickname;
    private DatePickerDialog datePickerDialog;
    private Calendar objTime;
    private TextView tv_sex;
    private int iYear;
    private int iMonth;
    private int iDay;
    private TextView tv_nickname_error;
    private TextView tv_nickname;

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
        llAge = (LinearLayout) findViewById(R.id.ll_userinformation_age);
        tv_nickname = (TextView) findViewById(R.id.tv_nickname);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        llchangenickname = (LinearLayout) findViewById(R.id.ll_change_nickname);
        llchangesex = (LinearLayout) findViewById(R.id.ll_change_sex);
        tvAge = (TextView) findViewById(R.id.tv_userinformation_age);
        llAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserInformationPresenter.llUserInformationAgeOnClick();
            }
        });
        llchangenickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserInformationPresenter.llUserInformationNickNameOnClick();
            }
        });
        llchangesex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserInformationPresenter.lluserInformationSexOnClick();
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
    public void starUserInformationAge() {
        DatePickerDialog.OnDateSetListener DatePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                objTime = Calendar.getInstance();
                iYear = objTime.get(Calendar.YEAR);
                iMonth = objTime.get(Calendar.MONTH);
                iDay = objTime.get(Calendar.DAY_OF_MONTH);
                if (iMonth > monthOfYear) {
                    tvAge.setText(iYear - year + "");
                } else if (iMonth < monthOfYear) {
                    tvAge.setText(iYear - year - 1 + "");
                } else {
                    if (iDay >= dayOfMonth) {
                        tvAge.setText(iYear - year + "");
                    } else {
                        tvAge.setText(iYear - year - 1 + "");
                    }
                }

            }
        };
        datePickerDialog = new DatePickerDialog(UserInformationActivity.this, DatePickerListener, 2000, 1, 1);
        datePickerDialog.setTitle("请选择出生日期");
        datePickerDialog.show();
    }

    @Override
    public void changeUserSex() {
        AlertDialog alertDialog = new AlertDialog.Builder(UserInformationActivity.this).create();
        alertDialog.setView(LayoutInflater.from(this).inflate(R.layout.dialog_change_sex_layout, null));
        alertDialog.show();
        Window window = alertDialog.getWindow();
        RadioGroup radioGroup = (RadioGroup) window.findViewById(R.id.rg_change_sex);
        if (tv_sex.getText().toString().equals("男"))
            radioGroup.check(R.id.rb_man);
        if (tv_sex.getText().toString().equals("女"))
            radioGroup.check(R.id.rb_woman);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_woman:
                        tv_sex.setText("女");
                        alertDialog.dismiss();
                        break;
                    case R.id.rb_man:
                        tv_sex.setText("男");
                        alertDialog.dismiss();
                        break;
                }
            }
        });
        showSnackBarMsg("修改成功");
    }

    @Override
    public void changeUserNickname() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setView(LayoutInflater.from(this).inflate(R.layout.dialog_change_nickname_layout, null));
        alertDialog.show();
        Window window = alertDialog.getWindow();
        EditText et_change_nickname = (EditText) window.findViewById(R.id.et_changed_nickname);
        Button btn_sure = (Button) window.findViewById(R.id.btn_dialog_sure);
        tv_nickname_error = (TextView) window.findViewById(R.id.tv_dialog_nickname_error);
        Button btn_cancel = (Button) window.findViewById(R.id.btn_dialog_cancled);
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_change_nickname.getText().toString().isEmpty())
                    tv_nickname_error.setText("昵称不能为空");
                else {
                    tv_nickname.setText(et_change_nickname.getText().toString().trim());
                    showSnackBarMsg("修改成功");
                    alertDialog.dismiss();
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
    }
}
