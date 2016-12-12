package com.swpuiot.stp.interfaces;

import com.swpuiot.stp.base.IView;

/**
 * Created by DELL on 2016/11/29.
 */
public interface ILoginedView extends IView {
    void gotoShoppingCarFragment();
    void startLoginedMenuSetting();

    void showMain();

    void startMyFragment();
}
