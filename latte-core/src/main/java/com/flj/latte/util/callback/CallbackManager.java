package com.flj.latte.util.callback;

import java.util.WeakHashMap;

/**
 * Created by 傅令杰
 */

public class CallbackManager {

    private static final WeakHashMap<Object, IGlobalCallback> CALLBACKS = new WeakHashMap<>();

    //Holder 帮助类
    private static class Holder {
        private static final CallbackManager INSTANCE = new CallbackManager();

    }

    //getInstance
    public static CallbackManager getInstance() {
        return Holder.INSTANCE;
    }

    public CallbackManager addCallback(Object tag, IGlobalCallback callback) {
        CALLBACKS.put(tag, callback);
        return this;
    }

    public IGlobalCallback getCallback(Object tag) {
        return CALLBACKS.get(tag);
    }


}
