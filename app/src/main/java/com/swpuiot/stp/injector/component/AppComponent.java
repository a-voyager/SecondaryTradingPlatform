package com.swpuiot.stp.injector.component;


import android.content.Context;

import com.swpuiot.stp.base.BaseApplication;
import com.swpuiot.stp.injector.module.AppModule;
import com.swpuiot.stp.injector.scrope.ContextLifeCycle;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wuhaojie on 2016/7/7 10:32.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    BaseApplication app();

    @ContextLifeCycle("App")
    Context context();

}
