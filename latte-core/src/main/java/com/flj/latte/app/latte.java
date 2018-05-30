package com.flj.latte.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by cguyu on 2018/5/13.
 */

public  class Latte {
    public static Configurator init(Context context) {
        getConfigurattons().put(ConfigTYpe.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }
    public static WeakHashMap<String, Object> getConfigurattons() {
        return Configurator.getInstance().getLatteConfigs();
    }
    public static Context getApplication(){
        return (Context) getConfigurattons().get(ConfigTYpe.APPLICATION_CONTEXT.name());
    }

    public static Context getApplicationContext(){
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT.name());

    }


}
