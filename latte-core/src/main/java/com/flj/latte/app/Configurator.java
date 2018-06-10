package com.flj.latte.app;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
 * Created by cguyu on 2018/5/13.
 */

public class Configurator {
    private static final WeakHashMap<Object, Object> LATTE_CONFIGS = new WeakHashMap<>();
    //字体库
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private static final Handler HANDLER = new Handler();//单例handle
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();//拦截器

    private Configurator() {

        LATTE_CONFIGS.put(ConfigKeys.APPLICATION_CONTEXT, false);
        LATTE_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);

    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final WeakHashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTORS, INTERCEPTORS);

        return this;
    }

    //
    public final Configurator withInterceptors(List<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTORS, INTERCEPTORS);

        return this;
    }

    public final Configurator withWeChatAppId(String appId) {

        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);

        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret) {

        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, appSecret);

        return this;
    }

    //wx的activity
    public final Configurator withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    //添加 JAVASCRIPT_INTERFACE html5加载的。
    public Configurator withJavascriptInterface(@NonNull String name) {
        LATTE_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY);

        if (!isReady)

        {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")

    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");

        }
        return (T) LATTE_CONFIGS.get(key);
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;

    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer =
                    Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }

        }
    }


}
