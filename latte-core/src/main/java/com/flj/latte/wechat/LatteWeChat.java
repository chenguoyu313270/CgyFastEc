package com.flj.latte.wechat;

import android.app.Activity;

import com.flj.latte.app.ConfigKeys;
import com.flj.latte.app.Latte;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
/**
 * Created by Administrator on 2018\6\4 0004.
 */

public class LatteWeChat {
    public static final String APP_ID = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
//    private IWeChatSignInCallback mSignInCallback = null;

    private static final class Holder {
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }
    private LatteWeChat() {
        final Activity activity = Latte.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }
    public static LatteWeChat getInstance() {
        return Holder.INSTANCE;
    }
//    public IWeChatSignInCallback getSignInCallback() {
//        return mSignInCallback;
//    }

//获取WXAPI 方法
    public final IWXAPI getWXAPI(){
        return WXAPI;
    }

    public  final void signIn(){
        final SendAuth.Req req=new SendAuth.Req();
        req.scope="snsapi_userinfo";
        req.state="random_state";
        WXAPI.sendReq(req);
    }

}

