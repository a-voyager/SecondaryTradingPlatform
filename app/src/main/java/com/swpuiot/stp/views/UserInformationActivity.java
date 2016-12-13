package com.swpuiot.stp.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;

public class UserInformationActivity extends BaseActivity implements IUserInformationView{
    @Inject
    UserInformationPresenter mUserInformationPresenter;
    @BindView(R.id.cl_user_information)
    CoordinatorLayout mClUserInformation;
    private LinearLayout llAge;
    private TextView tvAge;
    private DatePickerDialog datePickerDialog;
    private Calendar objTime;
    private int iYear;
    private int iMonth;
    private int iDay;
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
        llAge= (LinearLayout) findViewById(R.id.ll_userinformation_age);
        tvAge= (TextView) findViewById(R.id.tv_userinformation_age);
        llAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserInformationPresenter.llUserInformationAgeOnClick();
            }
        });
    }

    @Override
    public void showSnackBarMsg(@StringRes int msg) {
        SnackBarUtils.show(mClUserInformation,msg);
    }

    @Override
    public void showSnackBarMsg(String msg) {
        SnackBarUtils.show(mClUserInformation, msg);
    }


    @Override
    public void starUserInformationAge() {
        DatePickerDialog.OnDateSetListener DatePickerListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
                objTime = Calendar.getInstance();
                iYear = objTime.get(Calendar.YEAR);
                iMonth = objTime.get(Calendar.MONTH);
                iDay = objTime.get(Calendar.DAY_OF_MONTH);
                if(iMonth>monthOfYear){
                    tvAge.setText(iYear-year+"");
                }
                else if(iMonth<monthOfYear){
                    tvAge.setText(iYear-year-1+"");
                }
                else{
                    if (iDay>=dayOfMonth){
                        tvAge.setText(iYear-year+"");
                    }
                    else {
                        tvAge.setText(iYear-year-1+"");
                    }
                }

            }
        };
        datePickerDialog= new DatePickerDialog(UserInformationActivity.this, DatePickerListener,2000,1,1);
        datePickerDialog.setTitle("请选择出生日期");
        datePickerDialog.show();
    }
}
