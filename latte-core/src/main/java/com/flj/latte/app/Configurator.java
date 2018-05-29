package com.flj.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * Created by cguyu on 2018/5/13.
 */

public class Configurator {
    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();
    //字体库
    private static final ArrayList<IconFontDescriptor> ICONS=new ArrayList<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigTYpe.APPLICATION_CONTEXT.name(), false);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final WeakHashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        LATTE_CONFIGS.put(ConfigTYpe.CONFIG_READY.name(), true);
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigTYpe.API_HOST.name(), host);
        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigTYpe.CONFIG_READY.name());

        if (!isReady)

        {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }
   @SuppressWarnings("unchecked")

    final <T> T getConfiguration (Object key){
//        checkConfiguration();
//        return (T) LATTE_CONFIGS.get(key.name());
       checkConfiguration();
       final Object value = LATTE_CONFIGS.get(key);
       if (value == null) {
           throw new NullPointerException(key.toString() + " IS NULL");
       }
       return (T) LATTE_CONFIGS.get(key);
    }
    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;

    }
    private void initIcons(){
        if (ICONS.size()>0){
            final Iconify.IconifyInitializer initializer=
                    Iconify.with(ICONS.get(0));
            for (int i=1;i<ICONS.size();i++){
                initializer.with(ICONS.get(i));
            }

        }
    }



}
