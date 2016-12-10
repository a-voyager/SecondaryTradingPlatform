package com.swpuiot.stp.injector.component;


import android.app.Activity;
import android.content.Context;

import com.swpuiot.stp.injector.module.ActivityModule;
import com.swpuiot.stp.injector.scrope.ActivityScope;
import com.swpuiot.stp.injector.scrope.ContextLifeCycle;
import com.swpuiot.stp.presenter.impl.LoginedPresenter;
import com.swpuiot.stp.views.FindPasswordActivity;
import com.swpuiot.stp.views.LoginedActivity;
import com.swpuiot.stp.views.MainActivity;
import com.swpuiot.stp.views.MyActivity;
import com.swpuiot.stp.views.RegisterActivity;
import com.swpuiot.stp.views.SettingActivity;

import dagger.Component;


/**
 * Created by wuhaojie on 2016/7/7 10:57.
 */
@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
    void inject(LoginedActivity activity);
    void inject(RegisterActivity activity);
    void inject(MyActivity activity);
    void inject(FindPasswordActivity activity);
    void inject(SettingActivity activity);
    Activity activity();

    @ContextLifeCycle("Activity")
    Context activityContext();

    @ContextLifeCycle("App")
    Context appContext();


}
