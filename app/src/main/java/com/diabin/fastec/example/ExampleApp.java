package com.diabin.fastec.example;

import android.app.Application;

import com.flj.latte.app.Latte;
import com.flj.latte.net.interceptors.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import example.fastec.diabin.com.latte_ec.database.DatabaseManager;
import example.fastec.diabin.com.latte_ec.icon.FontEcModel;

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
                .configure();

        DatabaseManager.getInstance().init(this);//配置数据库
    }
}
