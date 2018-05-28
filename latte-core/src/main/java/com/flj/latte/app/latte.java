package com.flj.latte.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by cguyu on 2018/5/13.
 */

public final class latte {
    public static Configurator init(Context context) {
        getConfigurattons().put(ConfigTYpe.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static WeakHashMap<String, Object> getConfigurattons() {
        return Configurator.getInstance().getLatteConfigs();
    }
    public static Context getApplication(){
        return (Context) getConfigurattons().get(ConfigTYpe.APPLICATION_CONTEXT.name());
    }

}
