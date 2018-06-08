package com.flj.latte.delegates.web;

/**
 * Created by Administrator on 2018\6\8 0008.
 */

public class LatteWebInterface {
    private final WebDelegate DELEGATE;

    public LatteWebInterface(WebDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    //工程模式
    static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }
}
