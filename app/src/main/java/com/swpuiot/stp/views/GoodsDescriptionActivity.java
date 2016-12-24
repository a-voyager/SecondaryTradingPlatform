package com.swpuiot.stp.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.swpuiot.stp.Fragment.MainFragment;
import com.swpuiot.stp.R;
import com.swpuiot.stp.base.BaseActivity;
import com.swpuiot.stp.base.BaseApplication;
import com.swpuiot.stp.entities.GoodsEntity;
import com.swpuiot.stp.injector.component.ActivityComponent;
import com.swpuiot.stp.injector.component.DaggerActivityComponent;
import com.swpuiot.stp.injector.module.ActivityModule;
import com.swpuiot.stp.interfaces.IGoodsDescriptionView;
import com.swpuiot.stp.presenter.impl.FindPasswordPresenter;
import com.swpuiot.stp.presenter.impl.GoodsDescriptionPresenter;
import com.swpuiot.stp.utils.SnackBarUtils;

import java.net.URI;

import javax.inject.Inject;

import butterknife.BindView;

public class GoodsDescriptionActivity extends BaseActivity implements IGoodsDescriptionView {

    public static final String ADD_TO_SHPOOING_CAR = "addtoShoppingcar";
    private SimpleDraweeView sdv_goodsimg;
    private TextView tv_goods_description;
    private Button btn_addtoShoppingCar;
    private GoodsEntity goodsEntity;

    //    private LocalBroadcastManager localBroadcastManager;
    @Inject
    GoodsDescriptionPresenter mGoodsDescriptionPresenter;
    @BindView(R.id.cl_goodsdescription)
    CoordinatorLayout mClMain;


    @Override
    protected void initializePresenter() {
        mGoodsDescriptionPresenter.attachView(this);
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
        return R.layout.activity_goods_description;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        sdv_goodsimg = (SimpleDraweeView) findViewById(R.id.sdv_description_img);
        goodsEntity = (GoodsEntity) getIntent().getSerializableExtra(MainFragment.GoodsInfo);
        String s = "http://www.deardull.com/BookStore" + goodsEntity.getImgurl();
        Uri uri = Uri.parse(s);
        sdv_goodsimg.setImageURI(uri);
        tv_goods_description = (TextView) findViewById(R.id.tv_description_text);
        tv_goods_description.setText(goodsEntity.getDescription());
        btn_addtoShoppingCar = (Button) findViewById(R.id.btn_addtoShoppingCar);
        btn_addtoShoppingCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGoodsDescriptionPresenter.addToShoppingCarOnClick();
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
    public void addtoShoppingCar() {
//        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        showSnackBarMsg("添加成功");
        LoginedActivity.goodsEntityList.add(goodsEntity);
        Intent intent = new Intent("com.swpu.addtoshoppingcar");
        intent.putExtra(ADD_TO_SHPOOING_CAR, goodsEntity);
//        localBroadcastManager.sendBroadcast(intent);
        sendBroadcast(intent);
    }
}
