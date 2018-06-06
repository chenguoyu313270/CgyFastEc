package com.flj.latte.app;

import android.content.Context;
import android.os.Handler;

import java.util.WeakHashMap;

/**
 * Created by cguyu on 2018/5/13.
 */

public  class Latte {
    public static Configurator init(Context context) {
        getConfigurattons().put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }
    public static WeakHashMap<Object, Object> getConfigurattons() {
        return Configurator.getInstance().getLatteConfigs();
    }
    public static Context getApplication(){
        return (Context) getConfigurattons().get(ConfigKeys.APPLICATION_CONTEXT);
    }
    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }
    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }




}
