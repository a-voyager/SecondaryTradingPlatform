package com.swpuiot.stp.views;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.swpuiot.stp.Fragment.MainFragment;
import com.swpuiot.stp.Fragment.MyFragment;
import com.swpuiot.stp.Fragment.ShoppingFragment;
import com.swpuiot.stp.R;
import com.swpuiot.stp.base.BaseActivity;
import com.swpuiot.stp.base.BaseApplication;
import com.swpuiot.stp.injector.component.ActivityComponent;
import com.swpuiot.stp.injector.component.DaggerActivityComponent;
import com.swpuiot.stp.injector.module.ActivityModule;
import com.swpuiot.stp.interfaces.ILoginedView;
import com.swpuiot.stp.presenter.impl.LoginedPresenter;
import com.swpuiot.stp.utils.SnackBarUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class LoginedActivity extends BaseActivity implements ILoginedView {

    @Inject
    LoginedPresenter mLoginedPresenter;

    @BindView(R.id.cl_logined)
    CoordinatorLayout mClLogined;
    private Button showmain;
    private Button loginToMy;
    private Button shoppingcar;

    @Override
    protected void initializePresenter() {
        mLoginedPresenter.attachView(this);
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
        return R.layout.activity_logined;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mLoginedPresenter.onCreate(savedInstanceState);
        showmain = (Button) findViewById(R.id.btn_main);
        shoppingcar = (Button) findViewById(R.id.shopping_car);


        shoppingcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginedPresenter.btnShoppingcarOnClick();
            }
        });

        loginToMy = (Button) findViewById(R.id.btn_loginToMy);
        loginToMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginedPresenter.btnLoginToMyOnClick();
            }
        });

        showmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginedPresenter.btnMainOnClick();
            }
        });
    }

    @Override
    public void showSnackBarMsg(@StringRes int msg) {
        SnackBarUtils.show(mClLogined, msg);
    }

    @Override
    public void showSnackBarMsg(String msg) {
        SnackBarUtils.show(mClLogined, msg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_logined, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:
                mLoginedPresenter.itemLoginedSetting();

        }
        return true;
    }

    @Override
    public void startLoginToMyActivity() {
        MyFragment fragment = new MyFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.relativelayout1, fragment).commit();
    }

    @Override
    public void gotoShoppingCarFragment() {
        ShoppingFragment fragment = new ShoppingFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.relativelayout1, fragment).commit();
    }

    @Override
    public void showMain() {
        MainFragment fragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.relativelayout1, fragment).commit();
    }
    @Override
    public void startLoginedMenuSetting(){
        Intent intent=new Intent(this,SettingActivity.class);
        startActivity(intent);

    }
}
