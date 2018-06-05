package com.flj.latte.wechat.callbacks;

/** 微信登录成功后回调
 * Created by Administrator on 2018\6\5 0005.
 */

public interface IWeChatSignInCallback {
    void onSignInSuccess(String userInfo);
}
