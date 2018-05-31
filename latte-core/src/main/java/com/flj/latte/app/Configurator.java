package com.flj.latte.app;

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

    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();//拦截器

    private Configurator() {
        LATTE_CONFIGS.put(ConfigTYpe.APPLICATION_CONTEXT, false);
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
        LATTE_CONFIGS.put(ConfigTYpe.CONFIG_READY, true);
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigTYpe.API_HOST, host);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigTYpe.INTERCEPTORS, INTERCEPTORS);

        return this;
    }

    //
    public final Configurator withInterceptors(List<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigTYpe.INTERCEPTORS, INTERCEPTORS);

        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigTYpe.CONFIG_READY);

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
