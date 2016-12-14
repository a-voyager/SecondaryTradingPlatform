package com.swpuiot.stp.views;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;


import com.swpuiot.stp.Fragment.MainFragment;
import com.swpuiot.stp.Fragment.MyFragment;
import com.swpuiot.stp.Fragment.RecommendFragment;
import com.swpuiot.stp.Fragment.ShareFragment;
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
    private FragmentManager fragmentManager;
    private RadioGroup radioGroup;

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
        fragmentManager = getSupportFragmentManager();
        showMain();
        radioGroup = (RadioGroup) findViewById(R.id.rg_toolbar);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rb_main:
                        mLoginedPresenter.btnMainOnClick();
                        break;
                    case R.id.rb_recommend:
                        mLoginedPresenter.btnRecommendOnClick();
                        break;
                    case R.id.rb_share:
                        mLoginedPresenter.btnShareOnClick();
                        break;
                    case R.id.rb_shopping_car:
                        mLoginedPresenter.btnShoppingcarOnClick();
                        break;
                    case R.id.rb_myself:
                        mLoginedPresenter.btnMyOnClick();
                        break;
                }
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
    public void gotoShoppingCarFragment() {
        ShoppingFragment fragment = ShoppingFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.relativelayout1, fragment).commit();
    }

    @Override
    public void showMain() {
        MainFragment fragment = MainFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.relativelayout1, fragment).commit();
    }

    @Override
    public void startMyFragment() {
        MyFragment fragment = MyFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.relativelayout1, fragment).commit();
    }

    @Override
    public void startRecommendFragment() {
        RecommendFragment fragment = RecommendFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.relativelayout1, fragment).commit();
    }

    @Override
    public void startShareFragment() {
        ShareFragment fragment = ShareFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.relativelayout1, fragment).commit();
    }

    @Override
    public void startLoginedMenuSetting() {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }
}
