package com.swpuiot.stp.views;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.swpuiot.stp.Fragment.MainFragment;
import com.swpuiot.stp.Fragment.MyFragment;
import com.swpuiot.stp.Fragment.RecommendFragment;
import com.swpuiot.stp.Fragment.ShareFragment;
import com.swpuiot.stp.Fragment.ShoppingFragment;
import com.swpuiot.stp.R;
import com.swpuiot.stp.base.BaseActivity;
import com.swpuiot.stp.base.BaseApplication;
import com.swpuiot.stp.entities.FindGoodsEntity;
import com.swpuiot.stp.entities.GoodsEntity;
import com.swpuiot.stp.entities.ResponseEntity;
import com.swpuiot.stp.injector.component.ActivityComponent;
import com.swpuiot.stp.injector.component.DaggerActivityComponent;
import com.swpuiot.stp.injector.module.ActivityModule;
import com.swpuiot.stp.interfaces.ILoginedView;
import com.swpuiot.stp.presenter.impl.LoginedPresenter;
import com.swpuiot.stp.utils.SnackBarUtils;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.Iterator;

import javax.inject.Inject;

import butterknife.BindView;

public class LoginedActivity extends BaseActivity implements ILoginedView {

    @Inject
    LoginedPresenter mLoginedPresenter;

    @BindView(R.id.cl_logined)
    CoordinatorLayout mClLogined;
    private FragmentManager fragmentManager;
    private RadioGroup radioGroup;
    public static Toolbar toolbar;
    public static ArrayList<FindGoodsEntity> findGoodsEntities;

    //    public static Toolbar Mytoolbar;
    public static ArrayList<GoodsEntity> goodsEntityList;
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
        goodsEntityList = new ArrayList<>();
//        Mytoolbar = (Toolbar) findViewById(R.id.toolbar_logininmy);
//        Mytoolbar.setVisibility(View.INVISIBLE);
        toolbar = (Toolbar) findViewById(R.id.toolbar_loginedin);
        setSupportActionBar(toolbar);
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

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(this, "http://www.deardull.com/BookStore/pur?method=find12", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String s = new String(bytes);

                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonElement el = parser.parse(s);
                JsonArray jsonArray = null;
                if (el.isJsonArray()) {
                    jsonArray = el.getAsJsonArray();
                }
                findGoodsEntities = new ArrayList<>();
                Iterator it = jsonArray.iterator();
                while (it.hasNext()) {
                    JsonElement e = (JsonElement) it.next();
                    findGoodsEntities.add(gson.fromJson(e, FindGoodsEntity.class));
                }
                Log.e("LoginedPresenter",findGoodsEntities.toString());
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.e("LogintedPresenter","error");
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
