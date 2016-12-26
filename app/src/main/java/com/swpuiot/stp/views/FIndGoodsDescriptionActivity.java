package com.swpuiot.stp.views;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.swpuiot.stp.Fragment.RecommendFragment;
import com.swpuiot.stp.R;
import com.swpuiot.stp.base.BaseActivity;
import com.swpuiot.stp.base.BaseApplication;
import com.swpuiot.stp.entities.FindGoodsEntity;
import com.swpuiot.stp.injector.component.ActivityComponent;
import com.swpuiot.stp.injector.component.DaggerActivityComponent;
import com.swpuiot.stp.injector.module.ActivityModule;
import com.swpuiot.stp.interfaces.FindGoodsDescriptionView;
import com.swpuiot.stp.presenter.impl.FindGoodsDescriptionPresenter;
import com.swpuiot.stp.utils.SnackBarUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class FIndGoodsDescriptionActivity extends BaseActivity implements FindGoodsDescriptionView {
    @Inject
    FindGoodsDescriptionPresenter mfindGoodsDescriptionPresenter;
    @BindView(R.id.cl_findgoodsdescription)
    CoordinatorLayout mClMain;
    private TextView mName;
    private TextView mDescription;
    private TextView mQQ;
    private TextView mPhone;

    @Override
    protected void initializePresenter() {
        mfindGoodsDescriptionPresenter.attachView(this);
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
        return R.layout.activity_find_goods_description;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mName = (TextView) findViewById(R.id.tv_find_goods__name);
        mDescription = (TextView) findViewById(R.id.tv_find_goods__description);
        mQQ = (TextView) findViewById(R.id.tv_findgoods_QQ);
        mPhone = (TextView) findViewById(R.id.tv_findgoods_Phone);
        FindGoodsEntity findGoodsEntity = (FindGoodsEntity) getIntent().getSerializableExtra(RecommendFragment.FINDGOODSINFORMATION);
        mName.setText(findGoodsEntity.getName());
        mDescription.setText(findGoodsEntity.getDescription());
        mQQ.setText(findGoodsEntity.getQQ());
        mPhone.setText(findGoodsEntity.getPhone());
    }

    @Override
    public void showSnackBarMsg(@StringRes int msg) {
        SnackBarUtils.show(mClMain, msg);
    }

    @Override
    public void showSnackBarMsg(String msg) {
        SnackBarUtils.show(mClMain, msg);
    }
}
