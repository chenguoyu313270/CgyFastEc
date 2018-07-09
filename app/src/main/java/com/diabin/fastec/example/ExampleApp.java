package com.diabin.fastec.example;

import android.app.Application;

import com.flj.latte.app.Latte;
import com.flj.latte.ec.database.DatabaseManager;
import com.flj.latte.ec.icon.FontEcModel;
import com.flj.latte.net.interceptors.DebugInterceptor;
import com.flj.latte.util.callback.CallbackManager;
import com.flj.latte.util.callback.CallbackType;
import com.flj.latte.util.callback.IGlobalCallback;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mob.MobSDK;

import cn.jpush.android.api.JPushInterface;

//import example.fastec.diabin.com.latte_ec.database.DatabaseManager;
//import example.fastec.diabin.com.latte_ec.icon.FontEcModel;

/**
 * Created by Administrator on 2018\5\23 0023.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModel())

                .withApiHost("http://127.0.0.1/")
//                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .withJavascriptInterface("latte")//添加发现 的本地嵌套html5

                .configure();

        DatabaseManager.getInstance().init(this);//配置数据库

        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        CallbackManager.getInstance().addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
            @Override
            public void executeCallback(Object args) {
                if(JPushInterface.isPushStopped(Latte.getApplicationContext())){
                    //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Latte.getApplicationContext());

                }

            }
        }).addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
            @Override
            public void executeCallback(Object args) {
                if (!JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            JPushInterface.stopPush(Latte.getApplicationContext());
                        }
            }
        });

        MobSDK.init(this);

    }
}
