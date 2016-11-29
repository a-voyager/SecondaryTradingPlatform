package com.swpuiot.stp.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;

/**
 * Created by wuhaojie on 2016/7/7 10:44.
 */
public interface IView {

    @LayoutRes
    int getLayoutResID();

    void initViews(Bundle savedInstanceState);

    void showSnackBarMsg(@StringRes int msg);

    void showSnackBarMsg(String msg);
}
